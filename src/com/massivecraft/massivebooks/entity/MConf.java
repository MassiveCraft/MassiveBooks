package com.massivecraft.massivebooks.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.mcore.MCore;
import com.massivecraft.mcore.store.Entity;
import com.massivecraft.mcore.util.MUtil;
import com.massivecraft.mcore.util.PermUtil;

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
	
	private List<String> newPlayerCommands = MUtil.list("/book ensureall {p}");
	public List<String> getNewPlayerCommands() { return new ArrayList<String>(this.newPlayerCommands); }
	public void setNewPlayerCommands(List<String> newPlayerCommands) { this.newPlayerCommands = new ArrayList<String>(newPlayerCommands); this.changed(); }
	
	private Map<String, Double> permToCopyCost = MUtil.map(
		"massivebooks.copycost.free", 0D,
		"massivebooks.copycost.0", 0D,
		"massivebooks.copycost.0.01", 0.01D,
		"massivebooks.copycost.0.02", 0.02D,
		"massivebooks.copycost.0.03", 0.03D,
		"massivebooks.copycost.0.1", 0.1D,
		"massivebooks.copycost.0.2", 0.2D,
		"massivebooks.copycost.0.3", 0.3D,
		"massivebooks.copycost.1", 1D,
		"massivebooks.copycost.2", 2D,
		"massivebooks.copycost.3", 3D,
		"massivebooks.copycost.10", 10D,
		"massivebooks.copycost.20", 20D,
		"massivebooks.copycost.30", 30D,
		"massivebooks.copycost.default", 0D
	);
	public Map<String, Double> getPermToCopyCost() { return new LinkedHashMap<String, Double>(this.permToCopyCost); }
	public void setPermToCopyCost(Map<String, Double> permToCopyCost) { this.permToCopyCost = new LinkedHashMap<String, Double>(permToCopyCost); this.changed(); }
	public double getCopyCost(CommandSender sender)
	{
		if (sender == null) return 0;
		Map<String, Double> perm2val = this.getPermToCopyCost();
		if (perm2val == null) return 0;
		if (perm2val.size() == 0) return 0;
		return PermUtil.pickFirstVal(sender, perm2val);
	}
	
	// -------------------------------------------- //
	// UTILS
	// -------------------------------------------- //
	
	public void createUpdatePermissionNodes()
	{
		for (Entry<String, Double> entry : this.getPermToCopyCost().entrySet())
		{
			final String name = entry.getKey();
			final Double copyCost = entry.getValue();
			String description = String.format(Lang.PERMISSION_DESCRIPTION_COPYCOST_TEMPLATE, copyCost);
			PermUtil.get(true, true, name, description, PermissionDefault.FALSE);
		}
	}

}