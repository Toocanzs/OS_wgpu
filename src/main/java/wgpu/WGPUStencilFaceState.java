package wgpu;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import wgpu.WgpuLibrary.WGPUCompareFunction;
import wgpu.WgpuLibrary.WGPUStencilOperation;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("wgpu") 
public class WGPUStencilFaceState extends StructObject {
	public WGPUStencilFaceState() {
		super();
	}
	/// C type : WGPUCompareFunction
	@Field(0) 
	public IntValuedEnum<WGPUCompareFunction > compare() {
		return this.io.getEnumField(this, 0);
	}
	/// C type : WGPUCompareFunction
	@Field(0) 
	public WGPUStencilFaceState compare(IntValuedEnum<WGPUCompareFunction > compare) {
		this.io.setEnumField(this, 0, compare);
		return this;
	}
	/// C type : WGPUStencilOperation
	@Field(1) 
	public IntValuedEnum<WGPUStencilOperation > failOp() {
		return this.io.getEnumField(this, 1);
	}
	/// C type : WGPUStencilOperation
	@Field(1) 
	public WGPUStencilFaceState failOp(IntValuedEnum<WGPUStencilOperation > failOp) {
		this.io.setEnumField(this, 1, failOp);
		return this;
	}
	/// C type : WGPUStencilOperation
	@Field(2) 
	public IntValuedEnum<WGPUStencilOperation > depthFailOp() {
		return this.io.getEnumField(this, 2);
	}
	/// C type : WGPUStencilOperation
	@Field(2) 
	public WGPUStencilFaceState depthFailOp(IntValuedEnum<WGPUStencilOperation > depthFailOp) {
		this.io.setEnumField(this, 2, depthFailOp);
		return this;
	}
	/// C type : WGPUStencilOperation
	@Field(3) 
	public IntValuedEnum<WGPUStencilOperation > passOp() {
		return this.io.getEnumField(this, 3);
	}
	/// C type : WGPUStencilOperation
	@Field(3) 
	public WGPUStencilFaceState passOp(IntValuedEnum<WGPUStencilOperation > passOp) {
		this.io.setEnumField(this, 3, passOp);
		return this;
	}
	public WGPUStencilFaceState(Pointer pointer) {
		super(pointer);
	}
}
