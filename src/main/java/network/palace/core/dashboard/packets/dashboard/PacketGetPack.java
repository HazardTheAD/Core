package network.palace.core.dashboard.packets.dashboard;

import com.google.gson.JsonObject;
import lombok.Getter;
import network.palace.core.dashboard.packets.BasePacket;
import network.palace.core.dashboard.packets.PacketID;

import java.util.UUID;

/**
 * The type Packet get pack.
 */
public class PacketGetPack extends BasePacket {

    @Getter private UUID uuid = null;
    @Getter private String pack = "";

    /**
     * Instantiates a new Packet get pack.
     */
    public PacketGetPack() {
        this(null, "");
    }

    /**
     * Instantiates a new Packet get pack.
     *
     * @param uuid the uuid
     * @param pack the pack
     */
    public PacketGetPack(UUID uuid, String pack) {
        super(PacketID.Dashboard.GETPACK.getID());
        this.uuid = uuid;
        this.pack = pack;
    }

    public PacketGetPack fromJSON(JsonObject obj) {
        try {
            this.uuid = UUID.fromString(obj.get("uuid").getAsString());
        } catch (Exception e) {
            this.uuid = null;
        }
        this.pack = obj.get("pack").getAsString();
        return this;
    }

    public JsonObject getJSON() {
        JsonObject obj = new JsonObject();
        try {
            obj.addProperty("id", this.id);
            obj.addProperty("uuid", this.uuid.toString());
            obj.addProperty("pack", this.pack);
        } catch (Exception e) {
            return null;
        }
        return obj;
    }
}
