package wgpu;
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
@Library("wgpu") 
public class WGPURequiredLimits extends StructObject {
	public WGPURequiredLimits() {
		super();
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public Pointer<WGPUChainedStruct > nextInChain() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public WGPURequiredLimits nextInChain(Pointer<WGPUChainedStruct > nextInChain) {
		this.io.setPointerField(this, 0, nextInChain);
		return this;
	}
	/// C type : WGPULimits
	@Field(1) 
	public WGPULimits limits() {
		return this.io.getNativeObjectField(this, 1);
	}
	/// C type : WGPULimits
	@Field(1) 
	public WGPURequiredLimits limits(WGPULimits limits) {
		this.io.setNativeObjectField(this, 1, limits);
		return this;
	}
	public WGPURequiredLimits(Pointer pointer) {
		super(pointer);
	}
}
