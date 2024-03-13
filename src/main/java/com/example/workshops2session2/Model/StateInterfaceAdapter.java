package com.example.workshops2session2.Model;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StateInterfaceAdapter implements JsonSerializer<State>, JsonDeserializer<State>
{
  @Override public JsonElement serialize(State state, Type type,
      JsonSerializationContext jsonSerializationContext)
  {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("classType", state.getClass().getName());
    jsonObject.add("data", jsonSerializationContext.serialize(state));
    return jsonObject;
  }

  @Override public State deserialize(JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext)
      throws JsonParseException
  {
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    String className = jsonObject.get("classType").getAsString();
    JsonElement data = jsonObject.get("data");
    try
    {
      return jsonDeserializationContext.deserialize(data, Class.forName(className));
    }
    catch (ClassNotFoundException e)
    {
      throw new JsonParseException("Unknown element type: " + className, e);
    }
  }
}