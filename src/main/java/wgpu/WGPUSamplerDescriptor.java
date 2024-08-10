package wgpu;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import wgpu.WgpuLibrary.WGPUAddressMode;
import wgpu.WgpuLibrary.WGPUCompareFunction;
import wgpu.WgpuLibrary.WGPUFilterMode;
import wgpu.WgpuLibrary.WGPUMipmapFilterMode;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("wgpu") 
public class WGPUSamplerDescriptor extends StructObject {
	public WGPUSamplerDescriptor() {
		super();
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public Pointer<WGPUChainedStruct > nextInChain() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public WGPUSamplerDescriptor nextInChain(Pointer<WGPUChainedStruct > nextInChain) {
		this.io.setPointerField(this, 0, nextInChain);
		return this;
	}
	/// C type : const char*
	@Field(1) 
	public Pointer<Byte > label() {
		return this.io.getPointerField(this, 1);
	}
	/// C type : const char*
	@Field(1) 
	public WGPUSamplerDescriptor label(Pointer<Byte > label) {
		this.io.setPointerField(this, 1, label);
		return this;
	}
	/// C type : WGPUAddressMode
	@Field(2) 
	public IntValuedEnum<WGPUAddressMode > addressModeU() {
		return this.io.getEnumField(this, 2);
	}
	/// C type : WGPUAddressMode
	@Field(2) 
	public WGPUSamplerDescriptor addressModeU(IntValuedEnum<WGPUAddressMode > addressModeU) {
		this.io.setEnumField(this, 2, addressModeU);
		return this;
	}
	/// C type : WGPUAddressMode
	@Field(3) 
	public IntValuedEnum<WGPUAddressMode > addressModeV() {
		return this.io.getEnumField(this, 3);
	}
	/// C type : WGPUAddressMode
	@Field(3) 
	public WGPUSamplerDescriptor addressModeV(IntValuedEnum<WGPUAddressMode > addressModeV) {
		this.io.setEnumField(this, 3, addressModeV);
		return this;
	}
	/// C type : WGPUAddressMode
	@Field(4) 
	public IntValuedEnum<WGPUAddressMode > addressModeW() {
		return this.io.getEnumField(this, 4);
	}
	/// C type : WGPUAddressMode
	@Field(4) 
	public WGPUSamplerDescriptor addressModeW(IntValuedEnum<WGPUAddressMode > addressModeW) {
		this.io.setEnumField(this, 4, addressModeW);
		return this;
	}
	/// C type : WGPUFilterMode
	@Field(5) 
	public IntValuedEnum<WGPUFilterMode > magFilter() {
		return this.io.getEnumField(this, 5);
	}
	/// C type : WGPUFilterMode
	@Field(5) 
	public WGPUSamplerDescriptor magFilter(IntValuedEnum<WGPUFilterMode > magFilter) {
		this.io.setEnumField(this, 5, magFilter);
		return this;
	}
	/// C type : WGPUFilterMode
	@Field(6) 
	public IntValuedEnum<WGPUFilterMode > minFilter() {
		return this.io.getEnumField(this, 6);
	}
	/// C type : WGPUFilterMode
	@Field(6) 
	public WGPUSamplerDescriptor minFilter(IntValuedEnum<WGPUFilterMode > minFilter) {
		this.io.setEnumField(this, 6, minFilter);
		return this;
	}
	/// C type : WGPUMipmapFilterMode
	@Field(7) 
	public IntValuedEnum<WGPUMipmapFilterMode > mipmapFilter() {
		return this.io.getEnumField(this, 7);
	}
	/// C type : WGPUMipmapFilterMode
	@Field(7) 
	public WGPUSamplerDescriptor mipmapFilter(IntValuedEnum<WGPUMipmapFilterMode > mipmapFilter) {
		this.io.setEnumField(this, 7, mipmapFilter);
		return this;
	}
	@Field(8) 
	public float lodMinClamp() {
		return this.io.getFloatField(this, 8);
	}
	@Field(8) 
	public WGPUSamplerDescriptor lodMinClamp(float lodMinClamp) {
		this.io.setFloatField(this, 8, lodMinClamp);
		return this;
	}
	@Field(9) 
	public float lodMaxClamp() {
		return this.io.getFloatField(this, 9);
	}
	@Field(9) 
	public WGPUSamplerDescriptor lodMaxClamp(float lodMaxClamp) {
		this.io.setFloatField(this, 9, lodMaxClamp);
		return this;
	}
	/// C type : WGPUCompareFunction
	@Field(10) 
	public IntValuedEnum<WGPUCompareFunction > compare() {
		return this.io.getEnumField(this, 10);
	}
	/// C type : WGPUCompareFunction
	@Field(10) 
	public WGPUSamplerDescriptor compare(IntValuedEnum<WGPUCompareFunction > compare) {
		this.io.setEnumField(this, 10, compare);
		return this;
	}
	@Field(11) 
	public short maxAnisotropy() {
		return this.io.getShortField(this, 11);
	}
	@Field(11) 
	public WGPUSamplerDescriptor maxAnisotropy(short maxAnisotropy) {
		this.io.setShortField(this, 11, maxAnisotropy);
		return this;
	}
	public WGPUSamplerDescriptor(Pointer pointer) {
		super(pointer);
	}
}