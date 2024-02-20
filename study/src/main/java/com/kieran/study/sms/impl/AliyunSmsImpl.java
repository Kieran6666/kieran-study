package com.kieran.study.sms.impl;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.core.http.HttpHeaders;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendBatchSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendBatchSmsResponse;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.Gson;
import com.kieran.study.sms.AliyunSmsService;
import darabonba.core.RequestConfiguration;
import darabonba.core.client.ClientOverrideConfiguration;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 需导入Aliyun SDK + Aliyun 短信SDK
 */
@Service
public class AliyunSmsImpl implements AliyunSmsService {

    @Override
    public String sendSms(String phoneNumbers, String signName, String templateCode, String templateParam) throws ExecutionException, InterruptedException {

        System.err.println(phoneNumbers);
        System.err.println(signName);
        System.err.println(templateCode);
        System.err.println(templateParam);

        // HttpClient Configuration
        /*HttpClient httpClient = new ApacheAsyncHttpClientBuilder()
                .connectionTimeout(Duration.ofSeconds(10)) // Set the connection timeout time, the default is 10 seconds
                .responseTimeout(Duration.ofSeconds(10)) // Set the response timeout time, the default is 20 seconds
                .maxConnections(128) // Set the connection pool size
                .maxIdleTimeOut(Duration.ofSeconds(50)) // Set the connection pool timeout, the default is 30 seconds
                // Configure the proxy
                .proxy(new ProxyOptions(ProxyOptions.Type.HTTP, new InetSocketAddress("<your-proxy-hostname>", 9001))
                        .setCredentials("<your-proxy-username>", "<your-proxy-password>"))
                // If it is an https connection, you need to configure the certificate, or ignore the certificate(.ignoreSSL(true))
                .x509TrustManagers(new X509TrustManager[]{})
                .keyManagers(new KeyManager[]{})
                .ignoreSSL(false)
                .build();*/

        // Configure Credentials authentication information, including ak, secret, token
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId("LTAI5t7XP7GDmzepvasWAnvQ")
                .accessKeySecret("xNcURN7lVQUMKpUVQ7EWghE0LA1Z5q")
                //.securityToken("<your-token>") // use STS token
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .region("cn-hangzhou") // Region ID
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                        //.setConnectTimeout(Duration.ofSeconds(30))
                )
                .build();

        // Parameter settings for API request
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName(signName)
                .templateCode(templateCode)
                .phoneNumbers(phoneNumbers)
                .templateParam(templateParam)
                // Request-level configuration rewrite, can set Http request parameters, etc.
                // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                .build();

        System.err.println("=======");
        System.err.println(sendSmsRequest.getPhoneNumbers());
        System.err.println(sendSmsRequest.getSignName());
        System.err.println(sendSmsRequest.getTemplateCode());
        System.err.println(sendSmsRequest.getTemplateParam());

        // Asynchronously get the return value of the API request
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        // Synchronously get the return value of the API request
        SendSmsResponse resp = response.get();
        String res= new Gson().toJson(resp);
        // Asynchronous processing of return values
        /*response.thenAccept(resp -> {
            System.out.println(new Gson().toJson(resp));
        }).exceptionally(throwable -> { // Handling exceptions
            System.out.println(throwable.getMessage());
            return null;
        });*/

        // Finally, close the client
        client.close();

        return res;
    }

    @Override
    public String sendBatchSms(String phoneNumberJson, String signNameJson, String templateCode, String templateParamJson) throws ExecutionException, InterruptedException {
        // HttpClient Configuration
        /*HttpClient httpClient = new ApacheAsyncHttpClientBuilder()
                .connectionTimeout(Duration.ofSeconds(10)) // Set the connection timeout time, the default is 10 seconds
                .responseTimeout(Duration.ofSeconds(10)) // Set the response timeout time, the default is 20 seconds
                .maxConnections(128) // Set the connection pool size
                .maxIdleTimeOut(Duration.ofSeconds(50)) // Set the connection pool timeout, the default is 30 seconds
                // Configure the proxy
                .proxy(new ProxyOptions(ProxyOptions.Type.HTTP, new InetSocketAddress("<your-proxy-hostname>", 9001))
                        .setCredentials("<your-proxy-username>", "<your-proxy-password>"))
                // If it is an https connection, you need to configure the certificate, or ignore the certificate(.ignoreSSL(true))
                .x509TrustManagers(new X509TrustManager[]{})
                .keyManagers(new KeyManager[]{})
                .ignoreSSL(false)
                .build();*/
        System.err.println(phoneNumberJson);
        System.err.println(signNameJson);
        System.err.println(templateCode);
        System.err.println(templateParamJson);
        // Configure Credentials authentication information, including ak, secret, token
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId("LTAI5t7XP7GDmzepvasWAnvQ")
                .accessKeySecret("xNcURN7lVQUMKpUVQ7EWghE0LA1Z5q")
                //.securityToken("<your-token>") // use STS token
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .region("cn-hangzhou") // Region ID
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                        //.setConnectTimeout(Duration.ofSeconds(30))
                )
                .build();

        // Parameter settings for API request
        SendBatchSmsRequest sendBatchSmsRequest = SendBatchSmsRequest.builder()
                // Request-level configuration rewrite, can set Http request parameters, etc.
                .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                .phoneNumberJson(phoneNumberJson)
                .signNameJson(signNameJson)
                .templateCode(templateCode)
                .templateParamJson(templateParamJson)
                .build();


        System.err.println("=======");
        System.err.println(sendBatchSmsRequest.getPhoneNumberJson());
        System.err.println(sendBatchSmsRequest.getSignNameJson());
        System.err.println(sendBatchSmsRequest.getTemplateCode());
        System.err.println(sendBatchSmsRequest.getTemplateParamJson());

        // Asynchronously get the return value of the API request
        CompletableFuture<SendBatchSmsResponse> response = client.sendBatchSms(sendBatchSmsRequest);
        // 异步接收短信
//          response.thenAccept(resp -> {
//            System.out.println(new Gson().toJson(resp));
//        }).exceptionally(throwable -> { // Handling exceptions
//            System.out.println(throwable.getMessage());
//            return null;
//        });

        // Synchronously get the return value of the API request
        SendBatchSmsResponse resp = response.get();
        String res = new Gson().toJson(resp);

        // Finally, close the client
        client.close();

        return res;
    }
}
