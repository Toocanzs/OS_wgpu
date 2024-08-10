package wgpu;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import wgpu.WgpuLibrary.WGPUShaderStage;
/**
 * <i>native declaration : wgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("wgpu") 
public class WGPUShaderModuleGLSLDescriptor extends StructObject {
	public WGPUShaderModuleGLSLDescriptor() {
		super();
	}
	/// C type : WGPUChainedStruct
	@Field(0) 
	public WGPUChainedStruct chain() {
		return this.io.getNativeObjectField(this, 0);
	}
	/// C type : WGPUChainedStruct
	@Field(0) 
	public WGPUShaderModuleGLSLDescriptor chain(WGPUChainedStruct chain) {
		this.io.setNativeObjectField(this, 0, chain);
		return this;
	}
	/// C type : WGPUShaderStage
	@Field(1) 
	public IntValuedEnum<WGPUShaderStage > stage() {
		return this.io.getEnumField(this, 1);
	}
	/// C type : WGPUShaderStage
	@Field(1) 
	public WGPUShaderModuleGLSLDescriptor stage(IntValuedEnum<WGPUShaderStage > stage) {
		this.io.setEnumField(this, 1, stage);
		return this;
	}
	/// C type : const char*
	@Field(2) 
	public Pointer<Byte > code() {
		return this.io.getPointerField(this, 2);
	}
	/// C type : const char*
	@Field(2) 
	public WGPUShaderModuleGLSLDescriptor code(Pointer<Byte > code) {
		this.io.setPointerField(this, 2, code);
		return this;
	}
	@Field(3) 
	public int defineCount() {
		return this.io.getIntField(this, 3);
	}
	@Field(3) 
	public WGPUShaderModuleGLSLDescriptor defineCount(int defineCount) {
		this.io.setIntField(this, 3, defineCount);
		return this;
	}
	/// C type : WGPUShaderDefine*
	@Field(4) 
	public Pointer<WGPUShaderDefine > defines() {
		return this.io.getPointerField(this, 4);
	}
	/// C type : WGPUShaderDefine*
	@Field(4) 
	public WGPUShaderModuleGLSLDescriptor defines(Pointer<WGPUShaderDefine > defines) {
		this.io.setPointerField(this, 4, defines);
		return this;
	}
	public WGPUShaderModuleGLSLDescriptor(Pointer pointer) {
		super(pointer);
	}
}
