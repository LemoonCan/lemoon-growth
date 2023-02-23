//package cherry.service;
//
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayConstants;
//import com.alipay.api.internal.util.AlipaySignature;
//import com.alipay.api.internal.util.StringUtils;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author lee
// * @date 2022/6/9
// */
//public class SignTest {
//    public static void main(String[] args) throws AlipayApiException {
//        Map<String, String> params = new HashMap<>();
//        params.put("charset","UTF-8");
//        params.put("biz_content","{\"agreement_no\":\"20225409824512159116\",\"user_id\":\"2088612660476165\",\"invalid_time\":\"2115-02-01 00:00:00\",\"valid_time\":\"2022-06-09 17:10:09\",\"sign_time\":\"2022-06-09 17:10:09\",\"out_agreement_no\":\"DSC_2022060923292993340\",\"status\":\"ACTIVE\"}");
//        params.put("utc_timestamp","1654765813052");
//        params.put("sign","Uwe6zj+3/TtTAgD3n6VaHkgkXXf80AKJD39ML1842Q9vOQEUv+yAaXvcD7bHRY50v5kalwpqObX92Wf0hJgqWsJkGcHbFc35PjRKCD8leIpMePmzdu4k4ECwszSNzcbqTwNB4oqwKmWi1G+EMavhLJXq63n1LIBM6ccw34HKgE9IcHIys25tBjIE8GaNf9llpUfVkJs6ZiCCAnbxNNrMGm+vcyCFGElR/AhbJ3QLWudz45dO5JDM1B4eLgAvT79Rzhvwl0WuHzaYDje/7wuoKWS8mTBE9emZ+DufqsxoKFPs8BPwVX4te3ng8XpOiE5iS2vF9l7l4TfHK1MYXt6psg==");
//        params.put("app_id", "2021003129685128");
//        params.put("version", "1.1");
//        params.put("sign_type","RSA2");
//        params.put("notify_id", "2022060900262171013056307524600406");
//        params.put("msg_method","anttech.blockchain.finance.user.agreement.notify");
//
//        boolean signVerified = AlipaySignature.rsaCheckV1(params,
//                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAteldmKofhixA/hC/TA7phEslZtMU0awQh87p/S2trJJDxM0ou0tjP/uW4jraOLrl4luOQbUbAiOR5S8nOEN8XuDjNau9rkvlPwPFk1wnEybmZFLfzX6DdNe6/FcRLUYmcfIUjsfXg+NcCAoKy1fQPUIN/pnV4ALCADbEFmr7aKuo604VbcublvtrLQBjnOsRtHeVmnvNPJ2e9hZoQ0HwDK1kpXeY7oPDDaEnpO+p1c5RxkLBGuZmdzi4rqToSN5LSoYP8/fnfIjoRMa1VP7CXfplsu23WzCQ+JypTOXaWVc0UriwDJDZP2ZDnCbP7XNiI6ehAeI/XJe+bjqogZrEPQIDAQAB",
//                "utf-8",
//                "RSA2");
//
//        System.out.println(signVerified);
//
//
////        Map<String, String> map = new HashMap<>();
////        map.put("charset","GBK");
////        map.put("biz_content","<?xml version=\"1.0\" encoding=\"gbk\"?><XML><AppId><![CDATA[2021003129685128]]></AppId><FromUserId></FromUserId><CreateTime><![CDATA[1654770821143]]></CreateTime><MsgType><![CDATA[event]]></MsgType><EventType><![CDATA[verifygw]]></EventType><ActionParam></ActionParam><AgreementId></AgreementId><AccountNo></AccountNo></XML>");
////        map.put("service","alipay.service.check");
////        map.put("sign_type","RSA2");
////        map.put("sign", "iNHwkxtO8plxfP869wTttTWvJltVFPxkogzt+rI0UUBHlBNwGWppSYqgx6g+WpDn19yoR6S+D9/7+qtw///2dsd3NnNk3ifMhalhZBu2rE3IkgkVc9dDZO0vC6lUsiTmevP5vH77P8ZfkyWBKT1F4fh0B7a5v3YXpl2R9tQLxmKT+GdkbOU6p4W5gtLriyTkFWgichOicM98MNx2WGGuivrT17VGYyAzDAx/ivnhm4hq7aGza8tMuw3qxu1YL09oLiV8S2lfo2eW2dQTJHW29aAO4Q6LZFLmt0Xnd0q3o1dnS63gcRiYOQZP+cX1h7duPgLSBqsVc98bWbc4WPi6MA==");
////
////        boolean signVerified = AlipaySignature.rsaCheckV2(map,
////                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAteldmKofhixA/hC/TA7phEslZtMU0awQh87p/S2trJJDxM0ou0tjP/uW4jraOLrl4luOQbUbAiOR5S8nOEN8XuDjNau9rkvlPwPFk1wnEybmZFLfzX6DdNe6/FcRLUYmcfIUjsfXg+NcCAoKy1fQPUIN/pnV4ALCADbEFmr7aKuo604VbcublvtrLQBjnOsRtHeVmnvNPJ2e9hZoQ0HwDK1kpXeY7oPDDaEnpO+p1c5RxkLBGuZmdzi4rqToSN5LSoYP8/fnfIjoRMa1VP7CXfplsu23WzCQ+JypTOXaWVc0UriwDJDZP2ZDnCbP7XNiI6ehAeI/XJe+bjqogZrEPQIDAQAB",
////                "utf-8",
////                "RSA2");
////
////        System.out.println(signVerified);
//
//        String responseMsg = "<success>true</success>";
//        //对响应内容加签
//        responseMsg = SignTest.encryptAndSign(responseMsg,
//                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAteldmKofhixA/hC/TA7phEslZtMU0awQh87p/S2trJJDxM0ou0tjP/uW4jraOLrl4luOQbUbAiOR5S8nOEN8XuDjNau9rkvlPwPFk1wnEybmZFLfzX6DdNe6/FcRLUYmcfIUjsfXg+NcCAoKy1fQPUIN/pnV4ALCADbEFmr7aKuo604VbcublvtrLQBjnOsRtHeVmnvNPJ2e9hZoQ0HwDK1kpXeY7oPDDaEnpO+p1c5RxkLBGuZmdzi4rqToSN5LSoYP8/fnfIjoRMa1VP7CXfplsu23WzCQ+JypTOXaWVc0UriwDJDZP2ZDnCbP7XNiI6ehAeI/XJe+bjqogZrEPQIDAQAB",
//                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDkU8/C7aZZ+tUoIStjp7Gnw/6J6dC1xcyjPSLaUWbFZgk7zNi3P2iE6G5Xbnu2TTE6KEdBYOLZuTvsCmrcNVl/lwf2CAVqukeR60nimkbkzpHtncNJt++1FYcY+Zps6mPzg1DUmdybdRXHSKjiSC7byFEhWAn5MclldIkvh2cn0KlEOq03T9iFIfzNxrzcd5EWya8excnZQ89j374Z3v8xM9MEOT8WRxbaWATHSzCI7eT0jk45OPBf2+MSfX5RbBs3srZgSE6n2B+sRuqlM2JROWUDiO36x0XFy59CngL+W5U9WdUlVn0Xt/o8cRK0b+Nlhqjjo2yPOPg8OX5VleNZAgMBAAECggEAd9giAf+5RBtrjK+0VY9sqVEvpnXqja5v1fK9+3ekiNIg4JmJ2s1WcASL/ipIlwx3XT5NAFz5VSxr7l4dbs411smQbSwSo814yXeFhYJS2cAvSlh3a5c3I4ZULeC9lJOGuQdhAuZrq9FOCBb0Ril7JvNC2kyal4jxW+ZKJ39bvsJCIwI+zmCWA0R6Wd+aEakX5T+M0FqG8AV2LVMUKgMpZewNzDWragFg8TcyySMGNq4C6cWLJmB3Lm07Q5XGsrzXBFcTXKtIo+siooKavrYAQuvgo/yjLsjl1ytZcgX3pBWWveJoAvKFa1urV1IRe98gc+u0JgK6xsNCvdJNZXe6MQKBgQD+h45l3571Plbf1PNRwSFZh5EgcuRpO6MQdV5iUDJF+yEAL8ICG+226GtGzftga+1/6MI5L72mxFEef66z1cLMTq8oSPWoRJ94Y7AR/g2aYWJ8qXXZo6UBo61pEbgIL3CnDkg9IcdnPW69RtIZvYUVva2bnvrFXNjMecajaPxaXQKBgQDlpYDAWJiSVmzxqA6uGTE35E2JO2InJsCtpTLuKGrX2YFv7iMwr5/w4epzLbKtprcq7rmcjOdyTyGVd2uuxbp05KrCJnwo2H7GqXfqRf4/t7mmDSiZxg6yPuj1hD8dsFvblwxeyB125fRg3DA4koivQSGTCMIooC7od7rZI8v1LQKBgQCLiBZpBIDooocPZ3cH717v7KCDcFiE09udjynxrVMSCAxcRNIPQ+454gnFI/BVOHVXNGsc3G4UOPTbSlWkYl/Kjqv44p5A43WkksmX43Tjl9Vcz606ZTp0vpy2x67iVddkzTCOyrC8iPHnMybEU5/cfkHo2OtSPIbg+TUKqegFRQKBgEqPR5Qrr62jfc+f/tQrvb0l1L6/lwibHmfCgRyt5rKaHdpXePlvrAAreVFX/vE+da3swDrBzMtiQccjdMTT0cvuCjrq/9jLIa4ii5p2iyvo3M+66fmzQkZBAqXkABIFlm1fR1bmphpaYtOn6cgUW2h7PwlcCJ3kFBbJ+NJTOv8pAoGBANG7t3f0hWkklroK525raN+ZulogRbLcY2sM6OUybLNdlbTI/fAAPY/C2PuAqDwefy8g3vKadVE7NCIJ0yS7FMR7CbtVf3NlEgLDYfB27NUQNGPaYmfqQB5MUIrrGqR9cLIo9ZKXRA4evAWvRsP3dFBFMIkgx0m15eTgX7hilvKr", "",
//                false, true, "RSA2");
//
//        System.out.println(responseMsg);
//    }
//
//    public static String encryptAndSign(String bizContent, String alipayPublicKey, String cusPrivateKey, String charset,
//                                        boolean isEncrypt, boolean isSign, String signType) throws AlipayApiException {
//        StringBuilder sb = new StringBuilder();
//        if (StringUtils.isEmpty(charset)) {
//            charset = AlipayConstants.CHARSET_GBK;
//        }
//        sb.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>");
//        if (isEncrypt) {// 加密
//            sb.append("<alipay>");
//            String encrypted = AlipaySignature.rsaEncrypt(bizContent, alipayPublicKey, charset);
//            sb.append("<response>").append(encrypted).append("</response>");
//            sb.append("<encryption_type>AES</encryption_type>");
//            if (isSign) {
//                String sign = AlipaySignature.rsaSign(encrypted, cusPrivateKey, charset, signType);
//                sb.append("<sign>").append(sign).append("</sign>");
//                sb.append("<sign_type>");
//                sb.append(signType);
//                sb.append("</sign_type>");
//            }
//            sb.append("</alipay>");
//        } else if (isSign) {// 不加密，但需要签名
//            sb.append("<alipay>");
//            sb.append("<response>").append(bizContent).append("</response>");
//            String sign = AlipaySignature.rsaSign(bizContent, cusPrivateKey, charset, signType);
//            sb.append("<sign>").append(sign).append("</sign>");
//            sb.append("<sign_type>");
//            sb.append(signType);
//            sb.append("</sign_type>");
//            sb.append("</alipay>");
//        } else {// 不加密，不加签
//            sb.append(bizContent);
//        }
//        return sb.toString();
//    }
//}
