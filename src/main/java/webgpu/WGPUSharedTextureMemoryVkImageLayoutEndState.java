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
public class WGPUSharedTextureMemoryVkImageLayoutEndState extends StructObject {
	public WGPUSharedTextureMemoryVkImageLayoutEndState() {
		super();
	}
	/// C type : WGPUChainedStructOut
	@Field(0) 
	public WGPUChainedStructOut chain() {
		return this.io.getNativeObjectField(this, 0);
	}
	/// C type : WGPUChainedStructOut
	@Field(0) 
	public WGPUSharedTextureMemoryVkImageLayoutEndState chain(WGPUChainedStructOut chain) {
		this.io.setNativeObjectField(this, 0, chain);
		return this;
	}
	@Field(1) 
	public int oldLayout() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public WGPUSharedTextureMemoryVkImageLayoutEndState oldLayout(int oldLayout) {
		this.io.setIntField(this, 1, oldLayout);
		return this;
	}
	@Field(2) 
	public int newLayout() {
		return this.io.getIntField(this, 2);
	}
	@Field(2) 
	public WGPUSharedTextureMemoryVkImageLayoutEndState newLayout(int newLayout) {
		this.io.setIntField(this, 2, newLayout);
		return this;
	}
	public WGPUSharedTextureMemoryVkImageLayoutEndState(Pointer pointer) {
		super(pointer);
	}
}
