package com.massivecraft.massivebooks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;

import com.massivecraft.massivebooks.cmd.CmdBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.massivebooks.entity.MConfColl;
import com.massivecraft.mcore.MPlugin;

public class MassiveBooks extends MPlugin 
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MassiveBooks i;
	public static MassiveBooks get() { return i; }
	public MassiveBooks() { MassiveBooks.i = this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Commands
	private CmdBook cmdBook;
	public CmdBook getCmdBook() { return this.cmdBook; }
	
	// Vault Economy
	public Economy economy = null;
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Load Server Config
		ConfServer.get().load();
		
		// Initialize Collections
		MConfColl.get().init();
		MBookColl.get().init();
		
		// Setup Vault Economy
		this.setupEconomy();
		
		// Commands
		this.cmdBook = new CmdBook();
		this.cmdBook.register(this, true);
		
		// Setup Listeners
		MainListener.get().setup();
		PowertoolEngine.get().setup();
		
		postEnable();
	}
	
	private boolean setupEconomy()
	{
		RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null)
		{
			this.economy = economyProvider.getProvider();
			return true;
		}
		return false;
	}
	
}
