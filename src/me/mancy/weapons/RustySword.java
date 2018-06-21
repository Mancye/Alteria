package me.mancy.weapons;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.mancy.core.Main;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagString;

public class RustySword implements Listener {
	
	private Main plugin;
	private static ItemStack rustySword;
	public RustySword(Main main) {
		this.plugin = main;
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	private void createWeapon() {
		rustySword = new ItemStack(Material.STONE_SWORD);
		ItemMeta stoneSwordMeta = rustySword.getItemMeta();
		
		stoneSwordMeta.setDisplayName("Rusty Sword");
		stoneSwordMeta.setLore(Arrays.asList(ChatColor.GRAY + ChatColor.ITALIC.toString() + "A gift from hunter."));
		rustySword.setItemMeta(stoneSwordMeta);
		setAttackSpeed();
	}
	
	private void setAttackSpeed() {
		net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(rustySword);
		NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
		NBTTagList modifiers = new NBTTagList();
		NBTTagCompound attackSpeed = new NBTTagCompound();
		attackSpeed.set("AttributeName", new NBTTagString("generic.attackSpeed"));
		attackSpeed.set("Name", new NBTTagString("generic.attackSpeed"));
		attackSpeed.set("Amount", new NBTTagInt(20));
		attackSpeed.set("Operation", new NBTTagInt(0));
		attackSpeed.set("UUIDLeast", new NBTTagInt(894654));
		attackSpeed.set("UUIDMost", new NBTTagInt(2872));
		attackSpeed.set("Slot", new NBTTagString("mainhand"));
		modifiers.add(attackSpeed);
		compound.set("AttributeModifiers", modifiers);
		nmsStack.setTag(compound);
		rustySword = CraftItemStack.asBukkitCopy(nmsStack);
	}
	
	public static void giveItem(Player p) {
		p.getInventory().setItemInMainHand(rustySword);
	}
	
	@EventHandler
	private void onDamage(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) return;
		
		Player p = (Player) event.getDamager();
		LivingEntity e = (LivingEntity) event.getEntity();
		
		if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Rusty Sword")) {
			event.setCancelled(true);
			
		} else {
			return;
		}
	}
 	
}
