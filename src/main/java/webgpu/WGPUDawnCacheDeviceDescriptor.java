package webgpu;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import webgpu.WebgpuLibrary.WGPUDawnLoadCacheDataFunction;
import webgpu.WebgpuLibrary.WGPUDawnStoreCacheDataFunction;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUDawnCacheDeviceDescriptor extends StructObject {
	public WGPUDawnCacheDeviceDescriptor() {
		super();
	}
	/// C type : WGPUChainedStruct
	@Field(0) 
	public WGPUChainedStruct chain() {
		return this.io.getNativeObjectField(this, 0);
	}
	/// C type : WGPUChainedStruct
	@Field(0) 
	public WGPUDawnCacheDeviceDescriptor chain(WGPUChainedStruct chain) {
		this.io.setNativeObjectField(this, 0, chain);
		return this;
	}
	/// C type : const char*
	@Field(1) 
	public Pointer<Byte > isolationKey() {
		return this.io.getPointerField(this, 1);
	}
	/// C type : const char*
	@Field(1) 
	public WGPUDawnCacheDeviceDescriptor isolationKey(Pointer<Byte > isolationKey) {
		this.io.setPointerField(this, 1, isolationKey);
		return this;
	}
	/// C type : WGPUDawnLoadCacheDataFunction
	@Field(2) 
	public Pointer<WGPUDawnLoadCacheDataFunction > loadDataFunction() {
		return this.io.getPointerField(this, 2);
	}
	/// C type : WGPUDawnLoadCacheDataFunction
	@Field(2) 
	public WGPUDawnCacheDeviceDescriptor loadDataFunction(Pointer<WGPUDawnLoadCacheDataFunction > loadDataFunction) {
		this.io.setPointerField(this, 2, loadDataFunction);
		return this;
	}
	/// C type : WGPUDawnStoreCacheDataFunction
	@Field(3) 
	public Pointer<WGPUDawnStoreCacheDataFunction > storeDataFunction() {
		return this.io.getPointerField(this, 3);
	}
	/// C type : WGPUDawnStoreCacheDataFunction
	@Field(3) 
	public WGPUDawnCacheDeviceDescriptor storeDataFunction(Pointer<WGPUDawnStoreCacheDataFunction > storeDataFunction) {
		this.io.setPointerField(this, 3, storeDataFunction);
		return this;
	}
	/// C type : void*
	@Field(4) 
	public Pointer<? > functionUserdata() {
		return this.io.getPointerField(this, 4);
	}
	/// C type : void*
	@Field(4) 
	public WGPUDawnCacheDeviceDescriptor functionUserdata(Pointer<? > functionUserdata) {
		this.io.setPointerField(this, 4, functionUserdata);
		return this;
	}
	public WGPUDawnCacheDeviceDescriptor(Pointer pointer) {
		super(pointer);
	}
}