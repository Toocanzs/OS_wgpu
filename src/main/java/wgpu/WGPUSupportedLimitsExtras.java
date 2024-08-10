package wgpu;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * <i>native declaration : wgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("wgpu") 
public class WGPUSupportedLimitsExtras extends StructObject {
	public WGPUSupportedLimitsExtras() {
		super();
	}
	/// C type : WGPUChainedStructOut
	@Field(0) 
	public WGPUChainedStructOut chain() {
		return this.io.getNativeObjectField(this, 0);
	}
	/// C type : WGPUChainedStructOut
	@Field(0) 
	public WGPUSupportedLimitsExtras chain(WGPUChainedStructOut chain) {
		this.io.setNativeObjectField(this, 0, chain);
		return this;
	}
	/// C type : WGPUNativeLimits
	@Field(1) 
	public WGPUNativeLimits limits() {
		return this.io.getNativeObjectField(this, 1);
	}
	/// C type : WGPUNativeLimits
	@Field(1) 
	public WGPUSupportedLimitsExtras limits(WGPUNativeLimits limits) {
		this.io.setNativeObjectField(this, 1, limits);
		return this;
	}
	public WGPUSupportedLimitsExtras(Pointer pointer) {
		super(pointer);
	}
}
