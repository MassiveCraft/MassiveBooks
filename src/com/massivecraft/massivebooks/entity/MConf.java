package com.massivecraft.massivebooks.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.massivecraft.mcore.MCore;
import com.massivecraft.mcore.store.Entity;

public class MConf extends Entity<MConf, String>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	public static MConf get()
	{
		return MConfColl.get().get(MCore.INSTANCE);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public MConf load(MConf that)
	{
		this.newPlayerCommands = that.newPlayerCommands;
		this.permToCopyCost = that.permToCopyCost;
		
		return this;
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private List<String> newPlayerCommands = new ArrayList<String>();
	public List<String> getNewPlayerCommands() { return new ArrayList<String>(this.newPlayerCommands); }
	public void setNewPlayerCommands(List<String> newPlayerCommands) { this.newPlayerCommands = new ArrayList<String>(newPlayerCommands); this.changed(); }
	
	private Map<String, Double> permToCopyCost = new LinkedHashMap<String, Double>();
	public Map<String, Double> getPermToCopyCost() { return new LinkedHashMap<String, Double>(this.permToCopyCost); }
	public void setPermToCopyCost(Map<String, Double> permToCopyCost) { this.permToCopyCost = new LinkedHashMap<String, Double>(permToCopyCost); this.changed(); }

}