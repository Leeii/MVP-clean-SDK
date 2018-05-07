package com.leeiidesu.lib.base.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by dgg on 2017/11/16.
 */

class FastjsonFormatter {
	String format(String source) {
		return JSON.toJSONString(JSON.parseObject(source), SerializerFeature.PrettyFormat);
	}
}
