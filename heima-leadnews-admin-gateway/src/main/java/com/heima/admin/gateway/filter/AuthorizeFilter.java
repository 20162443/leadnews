package com.heima.admin.gateway.filter;

import com.heima.admin.gateway.utils.AppJwtUtil;
import io.jsonwebtoken.Claims;
import io.netty.handler.codec.http.multipart.HttpPostStandardRequestDecoder;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        if(request.getURI().getPath().contains("/login/in")){
            return chain.filter(exchange);
        }
        HttpHeaders headers=request.getHeaders();
        String jwtToken = headers.getFirst("token");
        if(StringUtils.isEmpty(jwtToken))
        {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        try {
            Claims claims = AppJwtUtil.getClaimsBody(jwtToken);
            int result=AppJwtUtil.verifyToken(claims);
            if(result==0||result==-1){
                Integer id =(Integer) claims.get("id");
                log.info("find userid:{} from uri:{}",id,request.getURI());
                ServerHttpRequest serverHttpRequest = request.mutate().headers(httpHeaders -> {
                    httpHeaders.add("userId", id + "");
                }).build();
                exchange.mutate().request(serverHttpRequest).build();

            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
