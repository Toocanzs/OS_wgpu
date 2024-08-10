package wgpu;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.ann.Ptr;
import wgpu.WgpuLibrary.WGPUDeviceLostCallback;
import wgpu.WgpuLibrary.WGPUFeatureName;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("wgpu") 
public class WGPUDeviceDescriptor extends StructObject {
	public WGPUDeviceDescriptor() {
		super();
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public Pointer<WGPUChainedStruct > nextInChain() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public WGPUDeviceDescriptor nextInChain(Pointer<WGPUChainedStruct > nextInChain) {
		this.io.setPointerField(this, 0, nextInChain);
		return this;
	}
	/// C type : const char*
	@Field(1) 
	public Pointer<Byte > label() {
		return this.io.getPointerField(this, 1);
	}
	/// C type : const char*
	@Field(1) 
	public WGPUDeviceDescriptor label(Pointer<Byte > label) {
		this.io.setPointerField(this, 1, label);
		return this;
	}
	@Ptr 
	@Field(2) 
	public long requiredFeatureCount() {
		return this.io.getSizeTField(this, 2);
	}
	@Ptr 
	@Field(2) 
	public WGPUDeviceDescriptor requiredFeatureCount(long requiredFeatureCount) {
		this.io.setSizeTField(this, 2, requiredFeatureCount);
		return this;
	}
	/// C type : const WGPUFeatureName*
	@Field(3) 
	public Pointer<IntValuedEnum<WGPUFeatureName > > requiredFeatures() {
		return this.io.getPointerField(this, 3);
	}
	/// C type : const WGPUFeatureName*
	@Field(3) 
	public WGPUDeviceDescriptor requiredFeatures(Pointer<IntValuedEnum<WGPUFeatureName > > requiredFeatures) {
		this.io.setPointerField(this, 3, requiredFeatures);
		return this;
	}
	/// C type : const WGPURequiredLimits*
	@Field(4) 
	public Pointer<WGPURequiredLimits > requiredLimits() {
		return this.io.getPointerField(this, 4);
	}
	/// C type : const WGPURequiredLimits*
	@Field(4) 
	public WGPUDeviceDescriptor requiredLimits(Pointer<WGPURequiredLimits > requiredLimits) {
		this.io.setPointerField(this, 4, requiredLimits);
		return this;
	}
	/// C type : WGPUQueueDescriptor
	@Field(5) 
	public WGPUQueueDescriptor defaultQueue() {
		return this.io.getNativeObjectField(this, 5);
	}
	/// C type : WGPUQueueDescriptor
	@Field(5) 
	public WGPUDeviceDescriptor defaultQueue(WGPUQueueDescriptor defaultQueue) {
		this.io.setNativeObjectField(this, 5, defaultQueue);
		return this;
	}
	/// C type : WGPUDeviceLostCallback
	@Field(6) 
	public Pointer<WGPUDeviceLostCallback > deviceLostCallback() {
		return this.io.getPointerField(this, 6);
	}
	/// C type : WGPUDeviceLostCallback
	@Field(6) 
	public WGPUDeviceDescriptor deviceLostCallback(Pointer<WGPUDeviceLostCallback > deviceLostCallback) {
		this.io.setPointerField(this, 6, deviceLostCallback);
		return this;
	}
	/// C type : void*
	@Field(7) 
	public Pointer<? > deviceLostUserdata() {
		return this.io.getPointerField(this, 7);
	}
	/// C type : void*
	@Field(7) 
	public WGPUDeviceDescriptor deviceLostUserdata(Pointer<? > deviceLostUserdata) {
		this.io.setPointerField(this, 7, deviceLostUserdata);
		return this;
	}
	public WGPUDeviceDescriptor(Pointer pointer) {
		super(pointer);
	}
}
