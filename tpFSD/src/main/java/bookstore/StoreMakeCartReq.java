package bookstore;

import io.atomix.catalyst.buffer.BufferInput;
import io.atomix.catalyst.buffer.BufferOutput;
import io.atomix.catalyst.serializer.Serializer;

public class StoreMakeCartReq extends Req {

	int clientId;

	public StoreMakeCartReq() {}
	
	public StoreMakeCartReq(int storeId, int clientId) {
		super(storeId);
		this.clientId = clientId;
	}

    @Override
    public void writeObject(BufferOutput<?> bufferOutput, Serializer serializer) {
    	super.writeObject(bufferOutput, serializer);
    	bufferOutput.writeInt(clientId);
    }

    @Override
    public void readObject(BufferInput<?> bufferInput, Serializer serializer) {
    	super.readObject(bufferInput, serializer);
    	this.clientId = bufferInput.readInt();
    }
}