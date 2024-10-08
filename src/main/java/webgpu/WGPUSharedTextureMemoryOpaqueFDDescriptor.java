package webgpu;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUSharedTextureMemoryOpaqueFDDescriptor extends StructObject {
	public WGPUSharedTextureMemoryOpaqueFDDescriptor() {
		super();
	}
	/// C type : WGPUChainedStruct
	@Field(0) 
	public WGPUChainedStruct chain() {
		return this.io.getNativeObjectField(this, 0);
	}
	/// C type : WGPUChainedStruct
	@Field(0) 
	public WGPUSharedTextureMemoryOpaqueFDDescriptor chain(WGPUChainedStruct chain) {
		this.io.setNativeObjectField(this, 0, chain);
		return this;
	}
	/// C type : const void*
	@Field(1) 
	public Pointer<? > vkImageCreateInfo() {
		return this.io.getPointerField(this, 1);
	}
	/// C type : const void*
	@Field(1) 
	public WGPUSharedTextureMemoryOpaqueFDDescriptor vkImageCreateInfo(Pointer<? > vkImageCreateInfo) {
		this.io.setPointerField(this, 1, vkImageCreateInfo);
		return this;
	}
	@Field(2) 
	public int memoryFD() {
		return this.io.getIntField(this, 2);
	}
	@Field(2) 
	public WGPUSharedTextureMemoryOpaqueFDDescriptor memoryFD(int memoryFD) {
		this.io.setIntField(this, 2, memoryFD);
		return this;
	}
	@Field(3) 
	public int memoryTypeIndex() {
		return this.io.getIntField(this, 3);
	}
	@Field(3) 
	public WGPUSharedTextureMemoryOpaqueFDDescriptor memoryTypeIndex(int memoryTypeIndex) {
		this.io.setIntField(this, 3, memoryTypeIndex);
		return this;
	}
	@Field(4) 
	public long allocationSize() {
		return this.io.getLongField(this, 4);
	}
	@Field(4) 
	public WGPUSharedTextureMemoryOpaqueFDDescriptor allocationSize(long allocationSize) {
		this.io.setLongField(this, 4, allocationSize);
		return this;
	}
	/// C type : WGPUBool
	@Field(5) 
	public int dedicatedAllocation() {
		return this.io.getIntField(this, 5);
	}
	/// C type : WGPUBool
	@Field(5) 
	public WGPUSharedTextureMemoryOpaqueFDDescriptor dedicatedAllocation(int dedicatedAllocation) {
		this.io.setIntField(this, 5, dedicatedAllocation);
		return this;
	}
	public WGPUSharedTextureMemoryOpaqueFDDescriptor(Pointer pointer) {
		super(pointer);
	}
}
