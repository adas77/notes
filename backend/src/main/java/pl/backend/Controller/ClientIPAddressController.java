package pl.backend.Controller;

import pl.backend.utils.HttpUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientIPAddressController {

    @RequestMapping(method = RequestMethod.GET, value = "/client-ip-address", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getClientIPAddress(HttpServletRequest request) {
        String ip = HttpUtils.getRequestIP(request);
        return "Client IP Address: " + ip;
    }
}
