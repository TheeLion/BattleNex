package com.battlenex.game.players.packets;

import com.battlenex.game.Client;
import com.battlenex.game.players.PacketType;

/**
 * Move Items
 **/
public class MoveItems implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int somejunk = c.getInStream().readUnsignedWordA(); // junk
		int itemFrom = c.getInStream().readUnsignedWordA();// slot1
		int itemTo = (c.getInStream().readUnsignedWordA() - 128);// slot2
		// c.sendMessage("junk: " + somejunk);
		if (c.inTrade) {
			c.getTradeAndDuel().declineTrade();
			return;
		}
		if (c.tradeStatus == 1) {
			c.getTradeAndDuel().declineTrade();
			return;
		}
		if (c.duelStatus == 1) {
			c.getTradeAndDuel().declineDuel();
			return;
		}
		c.getItems().moveItems(itemFrom, itemTo, somejunk);
	}
}
