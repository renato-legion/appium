//package org.example;
//
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.testng.annotations.Test;
//
//public class ApplicationAPI {
//
//    final String ROOT_URI = "https://azfdeu2badic01.azurefd.net/ux-mbbk-access-token-v2/channel/mbbk/v2/access-tokens/application-token";
//
//    final String ROOT_URI2 = "https://azfdeu2badic01.azurefd.net/ux-mbbk-user-authentication-v1/channel/mbbk/v1/user-authentications/parameters?parameterTypes=document,keyboard";
//
//    final String ROOT_URI3 = "https://azfdeu2badic01.azurefd.net/ux-mbbk-user-authentication-v1/channel/mbbk/v1/user-authentications/execute";
//
//    public String wardaConextID;
//   public  String tokenMBBKWarda;
//    public static void main(String[] args)
//    {
//        ApplicationAPI apiresponse = new ApplicationAPI();
//        apiresponse.response();
//    }
//
//    @Test
//    public void response ()
//    {
//        RestAssured.baseURI=ROOT_URI;
//        try{
//            Response response1 = RestAssured.given().log().all().relaxedHTTPSValidation()
//                    .contentType(ContentType.JSON)
//                    .header("credential-selector","MBBK")
//                    .header("token-request-id","eeff288a-616e-4b57-819b-5a03f0b688f1")
//                    .header("token-request-date","2022-03-01T17:15:20.509-0500")
//                    .header("force-token-generation","false")
//                    .header("token-request-signature","3a8c58e01c37d00b7d0dd39926e2727f20bef4c04a5b3d37acb3d96a2461720f")
//                    .post()
//        }catch (Exception e){
//
//
//        }
//
//    }
//
//
//
//}
