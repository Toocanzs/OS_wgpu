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
public class WGPUDawnTextureInternalUsageDescriptor extends StructObject {
	public WGPUDawnTextureInternalUsageDescriptor() {
		super();
	}
	/// C type : WGPUChainedStruct
	@Field(0) 
	public WGPUChainedStruct chain() {
		return this.io.getNativeObjectField(this, 0);
	}
	/// C type : WGPUChainedStruct
	@Field(0) 
	public WGPUDawnTextureInternalUsageDescriptor chain(WGPUChainedStruct chain) {
		this.io.setNativeObjectField(this, 0, chain);
		return this;
	}
	/// C type : WGPUTextureUsage
	@Field(1) 
	public long internalUsage() {
		return this.io.getLongField(this, 1);
	}
	/// C type : WGPUTextureUsage
	@Field(1) 
	public WGPUDawnTextureInternalUsageDescriptor internalUsage(long internalUsage) {
		this.io.setLongField(this, 1, internalUsage);
		return this;
	}
	public WGPUDawnTextureInternalUsageDescriptor(Pointer pointer) {
		super(pointer);
	}
}