//package cherry.service;
//
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.request.AnttechBlockchainDefinAssetmanagePenetrateSubmitRequest;
//import com.alipay.api.response.AnttechBlockchainDefinAssetmanagePenetrateSubmitResponse;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Test {
//    public static void main(String[] args) {
//        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
//                "2021003129685128",
//                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDkU8/C7aZZ+tUoIStjp7Gnw/6J6dC1xcyjPSLaUWbFZgk7zNi3P2iE6G5Xbnu2TTE6KEdBYOLZuTvsCmrcNVl/lwf2CAVqukeR60nimkbkzpHtncNJt++1FYcY+Zps6mPzg1DUmdybdRXHSKjiSC7byFEhWAn5MclldIkvh2cn0KlEOq03T9iFIfzNxrzcd5EWya8excnZQ89j374Z3v8xM9MEOT8WRxbaWATHSzCI7eT0jk45OPBf2+MSfX5RbBs3srZgSE6n2B+sRuqlM2JROWUDiO36x0XFy59CngL+W5U9WdUlVn0Xt/o8cRK0b+Nlhqjjo2yPOPg8OX5VleNZAgMBAAECggEAd9giAf+5RBtrjK+0VY9sqVEvpnXqja5v1fK9+3ekiNIg4JmJ2s1WcASL/ipIlwx3XT5NAFz5VSxr7l4dbs411smQbSwSo814yXeFhYJS2cAvSlh3a5c3I4ZULeC9lJOGuQdhAuZrq9FOCBb0Ril7JvNC2kyal4jxW+ZKJ39bvsJCIwI+zmCWA0R6Wd+aEakX5T+M0FqG8AV2LVMUKgMpZewNzDWragFg8TcyySMGNq4C6cWLJmB3Lm07Q5XGsrzXBFcTXKtIo+siooKavrYAQuvgo/yjLsjl1ytZcgX3pBWWveJoAvKFa1urV1IRe98gc+u0JgK6xsNCvdJNZXe6MQKBgQD+h45l3571Plbf1PNRwSFZh5EgcuRpO6MQdV5iUDJF+yEAL8ICG+226GtGzftga+1/6MI5L72mxFEef66z1cLMTq8oSPWoRJ94Y7AR/g2aYWJ8qXXZo6UBo61pEbgIL3CnDkg9IcdnPW69RtIZvYUVva2bnvrFXNjMecajaPxaXQKBgQDlpYDAWJiSVmzxqA6uGTE35E2JO2InJsCtpTLuKGrX2YFv7iMwr5/w4epzLbKtprcq7rmcjOdyTyGVd2uuxbp05KrCJnwo2H7GqXfqRf4/t7mmDSiZxg6yPuj1hD8dsFvblwxeyB125fRg3DA4koivQSGTCMIooC7od7rZI8v1LQKBgQCLiBZpBIDooocPZ3cH717v7KCDcFiE09udjynxrVMSCAxcRNIPQ+454gnFI/BVOHVXNGsc3G4UOPTbSlWkYl/Kjqv44p5A43WkksmX43Tjl9Vcz606ZTp0vpy2x67iVddkzTCOyrC8iPHnMybEU5/cfkHo2OtSPIbg+TUKqegFRQKBgEqPR5Qrr62jfc+f/tQrvb0l1L6/lwibHmfCgRyt5rKaHdpXePlvrAAreVFX/vE+da3swDrBzMtiQccjdMTT0cvuCjrq/9jLIa4ii5p2iyvo3M+66fmzQkZBAqXkABIFlm1fR1bmphpaYtOn6cgUW2h7PwlcCJ3kFBbJ+NJTOv8pAoGBANG7t3f0hWkklroK525raN+ZulogRbLcY2sM6OUybLNdlbTI/fAAPY/C2PuAqDwefy8g3vKadVE7NCIJ0yS7FMR7CbtVf3NlEgLDYfB27NUQNGPaYmfqQB5MUIrrGqR9cLIo9ZKXRA4evAWvRsP3dFBFMIkgx0m15eTgX7hilvKr",
//                "JSON",
//                "utf-8",
//                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApzm8gpALmzL8lG/NGO1LGiJKNPhp39pmDBdy5pTLXY/Mfre1rcbOKglL+Dnf3xEysKtjKP+dDeCOBBstiEUUgvGRC9X8hlGMpjihGC3+aDU/lIeXUI6zNlOB7t5cOSlkPAiqyWo/SWrW+6UzJNBIYIr0AGMLo5IADRND66oc5k3YKNl6umTr2lSu6mr0yVK0QwY+rFnF6H5zeh48mBTB6bFoPndWb3w0sOBVEMBoP7q+BKZ+EVkJpvwFl6f9jSjIMcONL0RXXQucFGg20YlmBHm94wr9LGnroENZoamndnD9h1qLu+E4hOxlbzE+pGiw9JEccHYUPsm/JCZlqrQjIwIDAQAB",
//                "RSA2"
//        );
//
//
//        AnttechBlockchainDefinAssetmanagePenetrateSubmitRequest request = new AnttechBlockchainDefinAssetmanagePenetrateSubmitRequest();
//        // ("{"+ "\"request_id\":\"deefc521b85a0c6507b5d5ea0d542993\","+ "\"function\":\"SIGN.SUBMIT\","+ "\"biz_params\":\"{\\\"inputParam1\\\":\\\"data1\\\", \\\"inputParam2\\\":\\\"data2\\\"}\""+ " }")
//        Map<String, String> map = new HashMap<>();
//
//        map.put("request_id", System.currentTimeMillis() + "");
//
////         map.put("function", "LOAN.APPLY");
//        map.put("function", "ORDER.APPLY");
////        map.put("function", "ORDER.CANCEL");
////        map.put("function", "ORDER.CANCEL");
//
//
//        Map<String, String> map2 = new HashMap<>();
//        map2.put("sceneCode", "DASOUCHE");
//        map2.put("subSceneCode", "0000000000015655");
//         map2.put("outOrderNo", "DSC_202205232329299329");
//        map2.put("channel", "ALIPAYAPP");
//        map2.put("customerName", "纪乒");
//        map2.put("userCertNo", "150103198508192925");
//        map2.put("penetrateId", "2022051800101101100221");
//
//        map2.put("verifyId", "ANTCHAIN");
//        map2.put("vidBizId", "ANTCHAIN");
//
//
//        map.put("biz_params", JSON.toJSONString(map2));
//
//        request.setBizContent(JSON.toJSONString(map));
//
//        try {
//
//            System.out.println(JSON.toJSONString(request));
//
//            AnttechBlockchainDefinAssetmanagePenetrateSubmitResponse response = alipayClient.execute(request);
//
//            System.out.println(JSON.toJSONString(response));
//            JSONObject jsonObject = JSON.parseObject(response.getBody());
//            String extInfo = JSON.parseObject(jsonObject.getJSONObject("anttech_blockchain_defin_assetmanage_penetrate_submit_response").getString("result_obj")).getString("extInfo");
//
//            System.out.println(extInfo);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//}
