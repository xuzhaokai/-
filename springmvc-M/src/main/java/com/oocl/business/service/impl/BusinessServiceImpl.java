package com.oocl.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.oocl.business.compontment.JerseyClient;
import com.oocl.business.enums.ResultStatusEnum;
import com.oocl.business.model.view.ComplaintView;
import com.oocl.business.util.CalendarUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.oocl.business.dao.BusinessDao;
import com.oocl.business.dao.StoreDao;
import com.oocl.business.enums.StatusEnum;
import com.oocl.business.model.Business;
import com.oocl.business.model.view.MerchantAuditView;
import com.oocl.business.model.RespondResult;
import com.oocl.business.model.Store;
import com.oocl.business.service.BusinessService;
import com.oocl.business.util.JsonUtil;
import com.oocl.business.util.PropertiesUtil;
import org.springframework.transaction.annotation.Transactional;

@Service("businessService")
@Transactional
public class BusinessServiceImpl implements BusinessService {

    private static final String DESITNATION="destination";
    private static final String COMPLAINT_ADDRESS="complaint.adress";

    @Resource(name = "businessDao")
    private BusinessDao businessDao;

    @Resource(name = "storeDao")
    private StoreDao storeDao;

    @Resource(name = "jerseyClient")
    private JerseyClient client;


    public RespondResult sendToAdminAndCreatStore(String account) {

        RespondResult respondResult = new RespondResult();
        MerchantAuditView merchantResp = new MerchantAuditView();
        Business business = businessDao.findByAccount(account);
        int statusBusiness = business.getStatus();//用户状态
        Map<String,Object> datas = new HashMap<String, Object>();


        if (statusBusiness == StatusEnum.BUSINESS_STATUS_NONE.getValue()){
            //没提交过 不查状态
            merchantResp.setStatus(StatusEnum.MERCHANT_STATUS_NO.getValue());//-1
        }else {
            //发A端
            merchantResp = sendToAdmin(account);
            if (merchantResp!=null && merchantResp.getId() != null) {
                //A端过来的是有数据的
                Store newStore = new Store();

                if(merchantResp.getStatus() == StatusEnum.MERCHANT_STATUS_AGREE.getValue()){
                    //审核通过 才有店铺id
                    if(statusBusiness == StatusEnum.BUSINESS_STATUS_SUCCESS.getValue()){
                        //提交过审核（1）就创建店铺，入库
                        Store store = new Store();
                        store.setMerchantStoreNumber(merchantResp.getStoreNumber());
                        store.setBusiness(business);//给店铺添加拥有者
                        store.setCreatedAt(CalendarUtil.getCurrentDateTime());
                        store.setName(merchantResp.getMerchantName());
                        store.setLocation(merchantResp.getStoreLocation());
                        newStore=storeDao.add(store);// 入库

                        if(newStore != null){
                            // 发送成功后 进行修改用户表的状态->已提交注册店铺并成功入库了（状态2）
                            business.setStatus(StatusEnum.BUSINESS_STATUS_INSTORE.getValue());
//                            businessDao.updateStatus(business);
                            datas.put("store_id",newStore.getId());
                        }

                    }else{
                        //其他情况 2 通过了
                        Store storeF = storeDao.find(merchantResp.getStoreNumber());
                        datas.put("store_id",storeF.getId());
                    }

                }


//                if (merchantResp.getStatus() == StatusEnum.MERCHANT_STATUS_AGREE.getValue()
//                        && statusBusiness == StatusEnum.BUSINESS_STATUS_SUCCESS.getValue()) {
//                    //A端返回状态为通过  而且 已经提交过注册（状态1）  进行店铺信息添加
//
//                    Store store = new Store();
//                    store.setMerchantStoreNumber(merchantResp.getStoreNumber());
//                    store.setBusiness(business);//给店铺添加拥有者
//                    store.setName(merchantResp.getMerchantName());
//                    store.setLocation(merchantResp.getStoreLocation());
//                    newStore=storeDao.add(store);// 入库
//
//                    // 发送成功后 进行修改用户表的状态->已提交注册店铺并成功入库了（状态2）
//                    business.setStatus(StatusEnum.BUSINESS_STATUS_INSTORE.getValue());
//                    businessDao.updateStatus(business);
//
//                }
//
//                if (merchantResp.getStatus() == 1) {//A端返回状态为通过 才能操作店铺 才去获取店铺iD
//
////                    storeDao.find(newStore.set)
//
//                    datas.put("store_id",newStore.getId());
//                }

            }else {
                //回来的没数据,查找失败
                merchantResp = new MerchantAuditView();
                merchantResp.setStatus(StatusEnum.MERCHANT_STATUS_NO.getValue());//-1
            }

        }


        merchantResp.setAccount(account);//账户是必须的
        datas.put("merchanMsg",merchantResp);
        respondResult.setDatas(datas);


        return respondResult;

    }

    // 发送http到A端获取注册信息
    public MerchantAuditView sendToAdmin(String account) {
        // 发HTTP到管理员端 去查询审核结果
        MerchantAuditView merchantResp = new MerchantAuditView();
        String respJson = client.sendHttp(PropertiesUtil.getProperty(DESITNATION)+account);
        if (!StringUtils.isBlank(respJson)) {
            merchantResp = JsonUtil.readValue(respJson, MerchantAuditView.class);
        }
        return merchantResp;
    }

    /**
     * the logic processing of registered business
     *
     * @param business
     * @return respondResult
     */

    public RespondResult register(Business business) {
        // TODO Auto-generated method stub
        System.out.println("in");
        Business businessIn = businessDao.findByAccount(business.getAccount());
        List<Store> list = new ArrayList<Store>();
        business.setStores(list);
        business.setStatus(0);
        business.setCreatedAt(CalendarUtil.getCurrentDateTime());
        RespondResult res = new RespondResult();
        //Return the browser header status to 200 (successful)
        res.setStatusCode(200);
        System.out.println(businessIn == null);
        //Check whether the account exists, then judge whether the account password is correct
        if (businessIn == null) {
            Business businessIn2 = businessDao.persist(business);
            System.out.println(businessIn2);
            if (businessIn2 != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("business", businessIn2);
                res.setDatas(map);
            } else {
                res.setMsg("注册失败，请重新注册");
            }
        } else {
            res.setMsg("注册失败，账号已存在");
        }
        return res;
    }

    /**
     * the logic processing of login business
     *
     * @param business
     * @return respondResult
     */

    public RespondResult login(Business business) {
        // TODO Auto-generated method stub
        Business businessResult = businessDao.findByAccount(business.getAccount());
        RespondResult res = new RespondResult();
        res.setStatusCode(200);
        if (businessResult != null) {
            if (businessResult.getPassword().equals(business.getPassword())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("business", business);
                res.setDatas(map);
            }else{
                res.setMsg("账号或密码名错误，请重试");
            }
        }else{
            res.setMsg("账号不存在，请重试");
        }
//        if (businessResult == null) {
//            res.setMsg("账号或密码名错误，请重试");
//        } else if (businessResult.getPassword().equals(business.getPassword())) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("business", business);
//            res.setDatas(map);
//        }
        return res;

    }
    /**
    *@Description 发http到A端去找投诉信息
    *@param
    *@return
    */
    public RespondResult findComplaintFormA(String storeId) {

        RespondResult resp = new RespondResult();

        Store store = new Store();
        store = storeDao.listOne(storeId);

        String respJson = client.sendHttp(PropertiesUtil.getProperty(COMPLAINT_ADDRESS)+store.getMerchantStoreNumber());

        if (!StringUtils.isBlank(respJson)) {
            ComplaintView complaint = new ComplaintView();
            Map<String, Object> map = new HashMap<String, Object>();
            List<ComplaintView> list = JsonUtil.readValue(respJson, List.class);
            map.put("list",list);
            resp.setDatas(map);
            resp.setStatusCode(ResultStatusEnum.OK.getCode());
            resp.setMsg(ResultStatusEnum.OK.getMsg());
        }else{
            resp.setStatusCode(ResultStatusEnum.FORBIDDEN.getCode());
            resp.setMsg(ResultStatusEnum.FORBIDDEN.getMsg());
        }

        return resp;
    }
}
