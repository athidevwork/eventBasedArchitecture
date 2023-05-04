package org.athi.eba.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.athi.eba.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class APIUtil {
    private static final Logger logger = LoggerFactory.getLogger(APIUtil.class);
    private static final Gson gson = new Gson();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void callRestApi(String srcApi, String destApi, String jsonStr) throws JsonProcessingException {
        //String jsonStr = null;
        System.out.println ("start - jsonStr for api " + destApi + " = " + jsonStr);
        jsonStr = getCallerJsonStr(srcApi, destApi, jsonStr);
        System.out.println ("jsonStr for api " + destApi + " = " + jsonStr);

        String apiUrl = null;
        switch (destApi) {
            case "Order":
                apiUrl = "http://localhost:8084/api/order/createOrder";
                break;
            case "Payment":
                apiUrl = "http://localhost:8085/api/payment/createPayment";
                break;
            case "Bill":
                apiUrl = "http://localhost:8086/api/bill/createBill";
                break;
            case "Report":
                apiUrl = "http://localhost:8087/api/report/createReport";
                break;
            case "Notify":
                apiUrl = "http://localhost:8088/api/notify/createNotify";
                break;
            default:
                logger.debug("Default API url - not handled.");
                break;
        }

        System.out.println("url = " + apiUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<String>(jsonStr, headers);
        System.out.println("jsonStr = " + jsonStr);
        System.out.println("-----Connecting to  = " + apiUrl + "-------");
        String resultAsJsonStr = restTemplate.postForObject(apiUrl, request, String.class);
        System.out.println("resultAsJsonStr = " + resultAsJsonStr);
        JsonNode root = objectMapper.readTree(resultAsJsonStr);
        System.out.println ("root = " + root.path("name").asText());
        System.out.println("root = " + root);
    }

    private static String getCallerJsonStr(String srcApi, String destApi, String jsonStr) {
        String type = null;
        Order order = null;
        Payment payment = null;

        if (srcApi.equals("Order")) {
            order = gson.fromJson(jsonStr, Order.class);
            type = "order";
            System.out.println("in order");
        }
        if (srcApi.equals("Payment")) {
            payment = gson.fromJson (jsonStr, Payment.class);
            type = "payment";
            System.out.println("in payment");
        }

        if (destApi.equals("Bill")) {
            if (type.equals("order")) {
                Bill bill = Bill.builder().customerName(order.getCustomerName()).paymentNumber("0").paymentAmount(BigDecimal.valueOf(0.00))
                            .orderNumber(order.getOrderId().toString()).orderAmount(order.getTotalAmount()).build();
                jsonStr = gson.toJson(bill);
            }
            else {
                Bill bill = Bill.builder().customerName(payment.getCustomerName()).orderNumber(payment.getOrderNumber())
                        .paymentNumber(payment.getPaymentId().toString()).orderAmount(payment.getOrderAmount())
                        .paymentAmount(payment.getPaymentAmount()).build();
                jsonStr = gson.toJson(bill);
            }
        }
        if (destApi.equals("Report")) {
            if (type.equals("order")) {
                Report report = Report.builder().customerName(order.getCustomerName()).paymentNumber("0").paymentAmount(BigDecimal.valueOf(0.00))
                        .orderNumber(order.getOrderId().toString()).orderAmount(order.getTotalAmount()).build();
                jsonStr = gson.toJson(report);
            }
            else {
                Report bill = Report.builder().customerName(payment.getCustomerName()).orderNumber(payment.getOrderNumber())
                        .paymentNumber(payment.getPaymentId().toString()).orderAmount(payment.getOrderAmount())
                        .paymentAmount(payment.getPaymentAmount()).build();
                jsonStr = gson.toJson(bill);
            }
        }
        if (destApi.equals("Notify")) {
            if (type.equals("order")) {
                Notify notify = Notify.builder().customerName(order.getCustomerName()).paymentNumber("0").paymentAmount(BigDecimal.valueOf(0.00))
                        .orderNumber(order.getOrderId().toString()).orderAmount(order.getTotalAmount()).build();
                jsonStr = gson.toJson(notify);
            }
            else {
                Notify notify = Notify.builder().customerName(payment.getCustomerName()).orderNumber(payment.getOrderNumber())
                        .paymentNumber(payment.getPaymentId().toString()).orderAmount(payment.getOrderAmount())
                        .paymentAmount(payment.getPaymentAmount()).build();
                jsonStr = gson.toJson(notify);
            }
        }

        return jsonStr;
    }
}
