package org.linshy.saas.admin.remote.dto;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.linshy.saas.admin.common.convention.result.Result;
import org.linshy.saas.admin.remote.dto.req.ShortLInkCreateReqDTO;
import org.linshy.saas.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.linshy.saas.admin.remote.dto.resp.ShortLInkCreateRespDTO;
import org.linshy.saas.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 短链接中台远程调用服务
 */
public interface ShortLinkRemoteService {

    /**
     * 创建短链接
     * @return 短链接信息
     */
    default Result<ShortLInkCreateRespDTO> createShortLink(@RequestBody ShortLInkCreateReqDTO requestParam)
    {
        String resultBodyStr = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/create",JSON.toJSONString(requestParam));
        return JSON.parseObject(resultBodyStr, new TypeReference<>() {
        });
    }


    /**
     * 短链接分页查询
     */
    default Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam)
    {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gid", requestParam.getGid());
        requestMap.put("current", requestParam.getCurrent());
        requestMap.put("size", requestParam.getSize());
        String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/page",requestMap);
        return JSON.parseObject(resultPageStr, new TypeReference<>() {
        });
    }

}
