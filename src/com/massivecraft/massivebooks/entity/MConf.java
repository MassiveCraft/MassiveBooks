package com.massivecraft.massivebooks.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissionDefault;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.mcore.MCore;
import com.massivecraft.mcore.store.Entity;
import com.massivecraft.mcore.util.MUtil;
import com.massivecraft.mcore.util.PermUtil;

public class MConf extends Entity<MConf>
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
		this.usingNewPlayerCommands = that.usingNewPlayerCommands;
		this.newPlayerCommands = that.newPlayerCommands;
		this.usingNewPlayerCommandsDelayTicks = that.usingNewPlayerCommandsDelayTicks;
		this.newPlayerCommandsDelayTicks = that.newPlayerCommandsDelayTicks;
		
		this.permToCopyCost = that.permToCopyCost;
		
		this.autoupdatingServerbooks = that.autoupdatingServerbooks;
		this.autoupdatingDisplayNames = that.autoupdatingDisplayNames;
		this.usingAuthorDisplayName = that.usingAuthorDisplayName;
		
		this.itemFrameLoadIfSneakTrue = that.itemFrameLoadIfSneakTrue;
		this.itemFrameLoadIfSneakFalse = that.itemFrameLoadIfSneakFalse;
		
		this.itemFrameDisplaynameIfSneakTrue = that.itemFrameDisplaynameIfSneakTrue;
		this.itemFrameDisplaynameIfSneakFalse = that.itemFrameDisplaynameIfSneakFalse;
		
		this.itemFrameRotateIfSneakTrue = that.itemFrameRotateIfSneakTrue;
		this.itemFrameRotateIfSneakFalse = that.itemFrameRotateIfSneakFalse;
		
		return this;
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// New Player Commands
	
	private boolean usingNewPlayerCommands = true;
	public boolean isUsingNewPlayerCommands() { return this.usingNewPlayerCommands; }
	public void setUsingNewPlayerCommands(boolean usingNewPlayerCommands) { this.usingNewPlayerCommands = usingNewPlayerCommands; this.changed(); }
	
	private List<String> newPlayerCommands = MUtil.list("/book give {p} ensure all");
	public List<String> getNewPlayerCommands() { return new ArrayList<String>(this.newPlayerCommands); }
	public void setNewPlayerCommands(List<String> newPlayerCommands) { this.newPlayerCommands = new ArrayList<String>(newPlayerCommands); this.changed(); }
	
	private boolean usingNewPlayerCommandsDelayTicks = true;
	public boolean isUsingNewPlayerCommandsDelayTicks() { return this.usingNewPlayerCommandsDelayTicks; }
	public void setUsingNewPlayerCommandsDelayTicks(boolean usingNewPlayerCommandsDelayTicks) { this.usingNewPlayerCommandsDelayTicks = usingNewPlayerCommandsDelayTicks; this.changed(); }
	
	private int newPlayerCommandsDelayTicks = 5;
	public int getNewPlayerCommandsDelayTicks() { return this.newPlayerCommandsDelayTicks; }
	public void setNewPlayerCommandsDelayTicks(int newPlayerCommandsDelayTicks) { this.newPlayerCommandsDelayTicks = newPlayerCommandsDelayTicks; this.changed(); }
	
	// Copy Cost
	
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
	public double getCopyCost(Permissible permissible)
	{
		Double ret = PermUtil.pickFirstVal(permissible, this.getPermToCopyCost());
		if (ret == null) ret = 0D;
		return ret;
	}
	
	// Auto Update
	
	private boolean autoupdatingServerbooks = true;
	public boolean isAutoupdatingServerbooks() { return this.autoupdatingServerbooks; }
	public void setAutoupdatingServerbooks(boolean autoupdatingServerbooks) { this.autoupdatingServerbooks = autoupdatingServerbooks; this.changed(); }
	
	private boolean autoupdatingDisplayNames = true;
	public boolean isAutoupdatingDisplayNames() { return this.autoupdatingDisplayNames; }
	public void setAutoupdatingDisplayNames(boolean autoupdatingDisplayNames) { this.autoupdatingDisplayNames = autoupdatingDisplayNames; this.changed(); }
	
	private boolean usingAuthorDisplayName = false;
	public boolean isUsingAuthorDisplayName() { return this.usingAuthorDisplayName; }
	public void setUsingAuthorDisplayName(boolean usingAuthorDisplayName) { this.usingAuthorDisplayName = usingAuthorDisplayName; this.changed(); }
	
	// ItemFrame Load
	
	private boolean itemFrameLoadIfSneakTrue = false;
	public boolean isItemFrameLoadIfSneakTrue() { return this.itemFrameLoadIfSneakTrue; }
	public void itemFrameLoadIfSneakTrue(boolean itemFrameLoadIfSneakTrue) { this.itemFrameLoadIfSneakTrue = itemFrameLoadIfSneakTrue; this.changed(); }
	
	private boolean itemFrameLoadIfSneakFalse = true;
	public boolean isItemFrameLoadIfSneakFalse() { return this.itemFrameLoadIfSneakFalse; }
	public void itemFrameLoadIfSneakFalse(boolean itemFrameLoadIfSneakFalse) { this.itemFrameLoadIfSneakFalse = itemFrameLoadIfSneakFalse; this.changed(); }

	// ItemFrame Displayname
	
	private boolean itemFrameDisplaynameIfSneakTrue = false;
	public boolean isItemFrameDisplaynameIfSneakTrue() { return this.itemFrameDisplaynameIfSneakTrue; }
	public void itemFrameDisplaynameIfSneakTrue(boolean itemFrameDisplaynameIfSneakTrue) { this.itemFrameDisplaynameIfSneakTrue = itemFrameDisplaynameIfSneakTrue; this.changed(); }
	
	private boolean itemFrameDisplaynameIfSneakFalse = true;
	public boolean isItemFrameDisplaynameIfSneakFalse() { return this.itemFrameDisplaynameIfSneakFalse; }
	public void itemFrameDisplaynameIfSneakFalse(boolean itemFrameDisplaynameIfSneakFalse) { this.itemFrameDisplaynameIfSneakFalse = itemFrameDisplaynameIfSneakFalse; this.changed(); }
	
	// ItemFrame Rotate
	
	private boolean itemFrameRotateIfSneakTrue = true;
	public boolean isItemFrameRotateIfSneakTrue() { return this.itemFrameRotateIfSneakTrue; }
	public void itemFrameRotateIfSneakTrue(boolean itemFrameRotateIfSneakTrue) { this.itemFrameRotateIfSneakTrue = itemFrameRotateIfSneakTrue; this.changed(); }
	
	private boolean itemFrameRotateIfSneakFalse = true;
	public boolean isItemFrameRotateIfSneakFalse() { return this.itemFrameRotateIfSneakFalse; }
	public void itemFrameRotateIfSneakFalse(boolean itemFrameRotateIfSneakFalse) { this.itemFrameRotateIfSneakFalse = itemFrameRotateIfSneakFalse; this.changed(); }
	
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