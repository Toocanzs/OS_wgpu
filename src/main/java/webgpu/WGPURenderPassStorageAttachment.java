package webgpu;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import webgpu.WebgpuLibrary.WGPULoadOp;
import webgpu.WebgpuLibrary.WGPUStoreOp;
import webgpu.WebgpuLibrary.WGPUTextureView;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPURenderPassStorageAttachment extends StructObject {
	public WGPURenderPassStorageAttachment() {
		super();
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public Pointer<WGPUChainedStruct > nextInChain() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public WGPURenderPassStorageAttachment nextInChain(Pointer<WGPUChainedStruct > nextInChain) {
		this.io.setPointerField(this, 0, nextInChain);
		return this;
	}
	@Field(1) 
	public long offset() {
		return this.io.getLongField(this, 1);
	}
	@Field(1) 
	public WGPURenderPassStorageAttachment offset(long offset) {
		this.io.setLongField(this, 1, offset);
		return this;
	}
	/// C type : WGPUTextureView
	@Field(2) 
	public WGPUTextureView storage() {
		return this.io.getTypedPointerField(this, 2);
	}
	/// C type : WGPUTextureView
	@Field(2) 
	public WGPURenderPassStorageAttachment storage(WGPUTextureView storage) {
		this.io.setPointerField(this, 2, storage);
		return this;
	}
	/// C type : WGPULoadOp
	@Field(3) 
	public IntValuedEnum<WGPULoadOp > loadOp() {
		return this.io.getEnumField(this, 3);
	}
	/// C type : WGPULoadOp
	@Field(3) 
	public WGPURenderPassStorageAttachment loadOp(IntValuedEnum<WGPULoadOp > loadOp) {
		this.io.setEnumField(this, 3, loadOp);
		return this;
	}
	/// C type : WGPUStoreOp
	@Field(4) 
	public IntValuedEnum<WGPUStoreOp > storeOp() {
		return this.io.getEnumField(this, 4);
	}
	/// C type : WGPUStoreOp
	@Field(4) 
	public WGPURenderPassStorageAttachment storeOp(IntValuedEnum<WGPUStoreOp > storeOp) {
		this.io.setEnumField(this, 4, storeOp);
		return this;
	}
	/// C type : WGPUColor
	@Field(5) 
	public WGPUColor clearValue() {
		return this.io.getNativeObjectField(this, 5);
	}
	/// C type : WGPUColor
	@Field(5) 
	public WGPURenderPassStorageAttachment clearValue(WGPUColor clearValue) {
		this.io.setNativeObjectField(this, 5, clearValue);
		return this;
	}
	public WGPURenderPassStorageAttachment(Pointer pointer) {
		super(pointer);
	}
}
