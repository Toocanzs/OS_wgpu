package webgpu;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import webgpu.WebgpuLibrary.WGPUAdapterType;
import webgpu.WebgpuLibrary.WGPUBackendType;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUAdapterProperties extends StructObject {
	public WGPUAdapterProperties() {
		super();
	}
	/// C type : WGPUChainedStructOut*
	@Field(0) 
	public Pointer<WGPUChainedStructOut > nextInChain() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : WGPUChainedStructOut*
	@Field(0) 
	public WGPUAdapterProperties nextInChain(Pointer<WGPUChainedStructOut > nextInChain) {
		this.io.setPointerField(this, 0, nextInChain);
		return this;
	}
	@Field(1) 
	public int vendorID() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public WGPUAdapterProperties vendorID(int vendorID) {
		this.io.setIntField(this, 1, vendorID);
		return this;
	}
	/// C type : const char*
	@Field(2) 
	public Pointer<Byte > vendorName() {
		return this.io.getPointerField(this, 2);
	}
	/// C type : const char*
	@Field(2) 
	public WGPUAdapterProperties vendorName(Pointer<Byte > vendorName) {
		this.io.setPointerField(this, 2, vendorName);
		return this;
	}
	/// C type : const char*
	@Field(3) 
	public Pointer<Byte > architecture() {
		return this.io.getPointerField(this, 3);
	}
	/// C type : const char*
	@Field(3) 
	public WGPUAdapterProperties architecture(Pointer<Byte > architecture) {
		this.io.setPointerField(this, 3, architecture);
		return this;
	}
	@Field(4) 
	public int deviceID() {
		return this.io.getIntField(this, 4);
	}
	@Field(4) 
	public WGPUAdapterProperties deviceID(int deviceID) {
		this.io.setIntField(this, 4, deviceID);
		return this;
	}
	/// C type : const char*
	@Field(5) 
	public Pointer<Byte > name() {
		return this.io.getPointerField(this, 5);
	}
	/// C type : const char*
	@Field(5) 
	public WGPUAdapterProperties name(Pointer<Byte > name) {
		this.io.setPointerField(this, 5, name);
		return this;
	}
	/// C type : const char*
	@Field(6) 
	public Pointer<Byte > driverDescription() {
		return this.io.getPointerField(this, 6);
	}
	/// C type : const char*
	@Field(6) 
	public WGPUAdapterProperties driverDescription(Pointer<Byte > driverDescription) {
		this.io.setPointerField(this, 6, driverDescription);
		return this;
	}
	/// C type : WGPUAdapterType
	@Field(7) 
	public IntValuedEnum<WGPUAdapterType > adapterType() {
		return this.io.getEnumField(this, 7);
	}
	/// C type : WGPUAdapterType
	@Field(7) 
	public WGPUAdapterProperties adapterType(IntValuedEnum<WGPUAdapterType > adapterType) {
		this.io.setEnumField(this, 7, adapterType);
		return this;
	}
	/// C type : WGPUBackendType
	@Field(8) 
	public IntValuedEnum<WGPUBackendType > backendType() {
		return this.io.getEnumField(this, 8);
	}
	/// C type : WGPUBackendType
	@Field(8) 
	public WGPUAdapterProperties backendType(IntValuedEnum<WGPUBackendType > backendType) {
		this.io.setEnumField(this, 8, backendType);
		return this;
	}
	/// C type : WGPUBool
	@Field(9) 
	public int compatibilityMode() {
		return this.io.getIntField(this, 9);
	}
	/// C type : WGPUBool
	@Field(9) 
	public WGPUAdapterProperties compatibilityMode(int compatibilityMode) {
		this.io.setIntField(this, 9, compatibilityMode);
		return this;
	}
	public WGPUAdapterProperties(Pointer pointer) {
		super(pointer);
	}
}
