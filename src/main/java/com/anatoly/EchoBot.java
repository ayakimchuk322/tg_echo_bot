package com.anatoly;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.botplatform.BotPlatform;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class EchoBot implements RequestHandler<Object, Object> {

    @Override
    public Object handleRequest(Object input, Context context) {

        var objMapper = new ObjectMapper();

        var tree = objMapper.valueToTree(input);
        var bodyNode = tree.get("body");
        var bodyText = bodyNode.asText();

        var platform = new BotPlatform("ECHO_BOT_ENV", "Echo bot");
        platform.initialize();
        platform.registerHandler("/echo", new EchoHandler());
        platform.handleEvent(bodyText);

        // Standard response type from AWS Toolkit project template
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        return new GatewayResponse(input.toString(), headers, 200);
    }
}
