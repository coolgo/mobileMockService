package utils.gson;

import java.lang.reflect.Type;

import models.RichPostReply;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ReplyGsonSerializer implements JsonSerializer<RichPostReply> {

	@Override
	public JsonElement serialize(RichPostReply src, Type arg1,
			JsonSerializationContext arg2) {
		ExclusionStrategy strategy = new CRExclusionStrategy();
		Gson gson = new GsonBuilder().setExclusionStrategies(strategy)
				.serializeNulls().create();
		return gson.toJsonTree(src);
	}

}
