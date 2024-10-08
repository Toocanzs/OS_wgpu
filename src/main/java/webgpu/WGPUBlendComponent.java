package webgpu;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import webgpu.WebgpuLibrary.WGPUBlendFactor;
import webgpu.WebgpuLibrary.WGPUBlendOperation;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUBlendComponent extends StructObject {
	public WGPUBlendComponent() {
		super();
	}
	/// C type : WGPUBlendOperation
	@Field(0) 
	public IntValuedEnum<WGPUBlendOperation > operation() {
		return this.io.getEnumField(this, 0);
	}
	/// C type : WGPUBlendOperation
	@Field(0) 
	public WGPUBlendComponent operation(IntValuedEnum<WGPUBlendOperation > operation) {
		this.io.setEnumField(this, 0, operation);
		return this;
	}
	/// C type : WGPUBlendFactor
	@Field(1) 
	public IntValuedEnum<WGPUBlendFactor > srcFactor() {
		return this.io.getEnumField(this, 1);
	}
	/// C type : WGPUBlendFactor
	@Field(1) 
	public WGPUBlendComponent srcFactor(IntValuedEnum<WGPUBlendFactor > srcFactor) {
		this.io.setEnumField(this, 1, srcFactor);
		return this;
	}
	/// C type : WGPUBlendFactor
	@Field(2) 
	public IntValuedEnum<WGPUBlendFactor > dstFactor() {
		return this.io.getEnumField(this, 2);
	}
	/// C type : WGPUBlendFactor
	@Field(2) 
	public WGPUBlendComponent dstFactor(IntValuedEnum<WGPUBlendFactor > dstFactor) {
		this.io.setEnumField(this, 2, dstFactor);
		return this;
	}
	public WGPUBlendComponent(Pointer pointer) {
		super(pointer);
	}
}
