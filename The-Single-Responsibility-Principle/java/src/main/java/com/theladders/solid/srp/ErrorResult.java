package com.theladders.solid.srp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// responsibilities
// - represents a result that has type error

public class ErrorResult extends Result
{
	private final List<String> errorList;
	
	public ErrorResult(String type, Map<String, Object> model, List<String> errorList)
	{
		super(type, model);
		this.errorList = errorList;
	}
	
	public Map<String, Object> toMap()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("errorList", errorList);
		map.put("type", getType());
		map.put("type", getModel());
		return map;
	}
	
	public List<String> getErrorList()
	{
		return errorList;
	}

}