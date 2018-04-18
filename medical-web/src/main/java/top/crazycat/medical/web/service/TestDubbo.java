package top.crazycat.medical.web.service;

import com.alibaba.fastjson.JSON;
import com.yyw.yhyc.bo.Pagination;
import com.yyw.yhyc.order.bo.ManufacturerOrder;
import com.yyw.yhyc.order.dto.OrderDto;
import com.yyw.yhyc.order.facade.ManufacturerOrderFacade;
import com.yyw.yhyc.order.facade.Order4ManagerFacade;
import com.yyw.yhyc.order.facade.OrderFacade;
import com.yyw.yhyc.order.facade.OrderIssuedFacade;
import com.yyw.yhyc.product.dto.ProductBeanDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.crazycat.medical.web.annotation.Log;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TestDubbo {

    Logger logger = LoggerFactory.getLogger(TestDubbo.class);

    @Resource
    private OrderIssuedFacade orderIssuedFacade;
    @Resource
    private OrderFacade orderFacade;
    @Resource
    private Order4ManagerFacade order4ManagerFacade;
    @Resource
    private ManufacturerOrderFacade manufacturerOrderFacade;

    @Log
    public Map<String, Object> testOrderIssued(Integer supplyId, String type) throws Exception {
        return orderIssuedFacade.findOrderIssuedListBySupplyId(supplyId,type);
    }

    @Log
    public Object testOrderList() throws Exception {
//        Pagination<OrderDto> pagination = new Pagination<>();
//        pagination.setPaginationFlag(true);
//        pagination.setPageNo(1);
//        pagination.setPageSize(20);
//
//        return order4ManagerFacade.listUnSendOrder(pagination,null);
        List<ManufacturerOrder> list = new ArrayList<>();
        ManufacturerOrder order = new ManufacturerOrder();
        order.setFlowId("XXD2018041316585074721");
        List<ProductBeanDto> plist = new ArrayList<>();
        ProductBeanDto d = new ProductBeanDto();
        d.setSendNum(1);
        d.setProduceCode("502AEDH190001");
        plist.add(d);
        order.setSendProductList(plist);
        list.add(order);
        String param = " [{\"courierNumber\":\"\",\"deliveryMethod\":1,\"errorMsg\":\"\",\"isSomeSend\":\"0\",\"orderStatus\":\"1\",\"supplyId\":94501,\"selectPartDeliverty\":\"0\",\n" +
                " \"deliveryDate\":\"2018-04-17 15:24:11\",\"sendProductList\":[{\"produceCode\":\"1600980495\",\"batchNumber\":\"111\",\"sendNum\":1,\"validUntil\":\"\"},\n" +
                " {\"produceCode\":\"1601180142\",\"batchNumber\":\"222\",\"sendNum\":1,\"validUntil\":\"\"},\n" +
                " {\"produceCode\":\"1600966769\",\"batchNumber\":\"333\",\"sendNum\":1,\"validUntil\":\"\"},\n" +
                " {\"produceCode\":\"1598970255\",\"batchNumber\":\"444\",\"sendNum\":1,\"validUntil\":\"\"}],\n" +
                " \"deliverTime\":\"2018-04-16 15:24:11\",\"supplyName\":\"琴琴的测试批发企业\",\"flowId\":\"XXD2018041316373474715\"},\n" +
                " {\"courierNumber\":\"\",\"deliveryMethod\":1,\"errorMsg\":\"\",\"isSomeSend\":\"0\",\"orderStatus\":\"1\",\"supplyId\":94501,\"selectPartDeliverty\":\"0\",\n" +
                " \"deliveryDate\":\"2018-04-17 15:24:11\",\"sendProductList\":[{\"produceCode\":\"1600980495\",\"batchNumber\":\"111\",\"sendNum\":1,\"validUntil\":\"\"},\n" +
                " {\"produceCode\":\"1601180142\",\"batchNumber\":\"222\",\"sendNum\":1,\"validUntil\":\"\"},\n" +
                " {\"produceCode\":\"1600966769\",\"batchNumber\":\"333\",\"sendNum\":1,\"validUntil\":\"\"},\n" +
                " {\"produceCode\":\"1598970255\",\"batchNumber\":\"444\",\"sendNum\":1,\"validUntil\":\"\"}],\n" +
                " \"deliverTime\":\"2018-04-16 15:24:11\",\"supplyName\":\"琴琴的测试批发企业\",\"flowId\":\"XXD2018041316585074721\"}]";

        return manufacturerOrderFacade.sendOrderDelivery(JSON.parseArray(param,ManufacturerOrder.class));
    }
}