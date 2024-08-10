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
public class WGPULimits extends StructObject {
	public WGPULimits() {
		super();
	}
	@Field(0) 
	public int maxTextureDimension1D() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public WGPULimits maxTextureDimension1D(int maxTextureDimension1D) {
		this.io.setIntField(this, 0, maxTextureDimension1D);
		return this;
	}
	@Field(1) 
	public int maxTextureDimension2D() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public WGPULimits maxTextureDimension2D(int maxTextureDimension2D) {
		this.io.setIntField(this, 1, maxTextureDimension2D);
		return this;
	}
	@Field(2) 
	public int maxTextureDimension3D() {
		return this.io.getIntField(this, 2);
	}
	@Field(2) 
	public WGPULimits maxTextureDimension3D(int maxTextureDimension3D) {
		this.io.setIntField(this, 2, maxTextureDimension3D);
		return this;
	}
	@Field(3) 
	public int maxTextureArrayLayers() {
		return this.io.getIntField(this, 3);
	}
	@Field(3) 
	public WGPULimits maxTextureArrayLayers(int maxTextureArrayLayers) {
		this.io.setIntField(this, 3, maxTextureArrayLayers);
		return this;
	}
	@Field(4) 
	public int maxBindGroups() {
		return this.io.getIntField(this, 4);
	}
	@Field(4) 
	public WGPULimits maxBindGroups(int maxBindGroups) {
		this.io.setIntField(this, 4, maxBindGroups);
		return this;
	}
	@Field(5) 
	public int maxBindGroupsPlusVertexBuffers() {
		return this.io.getIntField(this, 5);
	}
	@Field(5) 
	public WGPULimits maxBindGroupsPlusVertexBuffers(int maxBindGroupsPlusVertexBuffers) {
		this.io.setIntField(this, 5, maxBindGroupsPlusVertexBuffers);
		return this;
	}
	@Field(6) 
	public int maxBindingsPerBindGroup() {
		return this.io.getIntField(this, 6);
	}
	@Field(6) 
	public WGPULimits maxBindingsPerBindGroup(int maxBindingsPerBindGroup) {
		this.io.setIntField(this, 6, maxBindingsPerBindGroup);
		return this;
	}
	@Field(7) 
	public int maxDynamicUniformBuffersPerPipelineLayout() {
		return this.io.getIntField(this, 7);
	}
	@Field(7) 
	public WGPULimits maxDynamicUniformBuffersPerPipelineLayout(int maxDynamicUniformBuffersPerPipelineLayout) {
		this.io.setIntField(this, 7, maxDynamicUniformBuffersPerPipelineLayout);
		return this;
	}
	@Field(8) 
	public int maxDynamicStorageBuffersPerPipelineLayout() {
		return this.io.getIntField(this, 8);
	}
	@Field(8) 
	public WGPULimits maxDynamicStorageBuffersPerPipelineLayout(int maxDynamicStorageBuffersPerPipelineLayout) {
		this.io.setIntField(this, 8, maxDynamicStorageBuffersPerPipelineLayout);
		return this;
	}
	@Field(9) 
	public int maxSampledTexturesPerShaderStage() {
		return this.io.getIntField(this, 9);
	}
	@Field(9) 
	public WGPULimits maxSampledTexturesPerShaderStage(int maxSampledTexturesPerShaderStage) {
		this.io.setIntField(this, 9, maxSampledTexturesPerShaderStage);
		return this;
	}
	@Field(10) 
	public int maxSamplersPerShaderStage() {
		return this.io.getIntField(this, 10);
	}
	@Field(10) 
	public WGPULimits maxSamplersPerShaderStage(int maxSamplersPerShaderStage) {
		this.io.setIntField(this, 10, maxSamplersPerShaderStage);
		return this;
	}
	@Field(11) 
	public int maxStorageBuffersPerShaderStage() {
		return this.io.getIntField(this, 11);
	}
	@Field(11) 
	public WGPULimits maxStorageBuffersPerShaderStage(int maxStorageBuffersPerShaderStage) {
		this.io.setIntField(this, 11, maxStorageBuffersPerShaderStage);
		return this;
	}
	@Field(12) 
	public int maxStorageTexturesPerShaderStage() {
		return this.io.getIntField(this, 12);
	}
	@Field(12) 
	public WGPULimits maxStorageTexturesPerShaderStage(int maxStorageTexturesPerShaderStage) {
		this.io.setIntField(this, 12, maxStorageTexturesPerShaderStage);
		return this;
	}
	@Field(13) 
	public int maxUniformBuffersPerShaderStage() {
		return this.io.getIntField(this, 13);
	}
	@Field(13) 
	public WGPULimits maxUniformBuffersPerShaderStage(int maxUniformBuffersPerShaderStage) {
		this.io.setIntField(this, 13, maxUniformBuffersPerShaderStage);
		return this;
	}
	@Field(14) 
	public long maxUniformBufferBindingSize() {
		return this.io.getLongField(this, 14);
	}
	@Field(14) 
	public WGPULimits maxUniformBufferBindingSize(long maxUniformBufferBindingSize) {
		this.io.setLongField(this, 14, maxUniformBufferBindingSize);
		return this;
	}
	@Field(15) 
	public long maxStorageBufferBindingSize() {
		return this.io.getLongField(this, 15);
	}
	@Field(15) 
	public WGPULimits maxStorageBufferBindingSize(long maxStorageBufferBindingSize) {
		this.io.setLongField(this, 15, maxStorageBufferBindingSize);
		return this;
	}
	@Field(16) 
	public int minUniformBufferOffsetAlignment() {
		return this.io.getIntField(this, 16);
	}
	@Field(16) 
	public WGPULimits minUniformBufferOffsetAlignment(int minUniformBufferOffsetAlignment) {
		this.io.setIntField(this, 16, minUniformBufferOffsetAlignment);
		return this;
	}
	@Field(17) 
	public int minStorageBufferOffsetAlignment() {
		return this.io.getIntField(this, 17);
	}
	@Field(17) 
	public WGPULimits minStorageBufferOffsetAlignment(int minStorageBufferOffsetAlignment) {
		this.io.setIntField(this, 17, minStorageBufferOffsetAlignment);
		return this;
	}
	@Field(18) 
	public int maxVertexBuffers() {
		return this.io.getIntField(this, 18);
	}
	@Field(18) 
	public WGPULimits maxVertexBuffers(int maxVertexBuffers) {
		this.io.setIntField(this, 18, maxVertexBuffers);
		return this;
	}
	@Field(19) 
	public long maxBufferSize() {
		return this.io.getLongField(this, 19);
	}
	@Field(19) 
	public WGPULimits maxBufferSize(long maxBufferSize) {
		this.io.setLongField(this, 19, maxBufferSize);
		return this;
	}
	@Field(20) 
	public int maxVertexAttributes() {
		return this.io.getIntField(this, 20);
	}
	@Field(20) 
	public WGPULimits maxVertexAttributes(int maxVertexAttributes) {
		this.io.setIntField(this, 20, maxVertexAttributes);
		return this;
	}
	@Field(21) 
	public int maxVertexBufferArrayStride() {
		return this.io.getIntField(this, 21);
	}
	@Field(21) 
	public WGPULimits maxVertexBufferArrayStride(int maxVertexBufferArrayStride) {
		this.io.setIntField(this, 21, maxVertexBufferArrayStride);
		return this;
	}
	@Field(22) 
	public int maxInterStageShaderComponents() {
		return this.io.getIntField(this, 22);
	}
	@Field(22) 
	public WGPULimits maxInterStageShaderComponents(int maxInterStageShaderComponents) {
		this.io.setIntField(this, 22, maxInterStageShaderComponents);
		return this;
	}
	@Field(23) 
	public int maxInterStageShaderVariables() {
		return this.io.getIntField(this, 23);
	}
	@Field(23) 
	public WGPULimits maxInterStageShaderVariables(int maxInterStageShaderVariables) {
		this.io.setIntField(this, 23, maxInterStageShaderVariables);
		return this;
	}
	@Field(24) 
	public int maxColorAttachments() {
		return this.io.getIntField(this, 24);
	}
	@Field(24) 
	public WGPULimits maxColorAttachments(int maxColorAttachments) {
		this.io.setIntField(this, 24, maxColorAttachments);
		return this;
	}
	@Field(25) 
	public int maxColorAttachmentBytesPerSample() {
		return this.io.getIntField(this, 25);
	}
	@Field(25) 
	public WGPULimits maxColorAttachmentBytesPerSample(int maxColorAttachmentBytesPerSample) {
		this.io.setIntField(this, 25, maxColorAttachmentBytesPerSample);
		return this;
	}
	@Field(26) 
	public int maxComputeWorkgroupStorageSize() {
		return this.io.getIntField(this, 26);
	}
	@Field(26) 
	public WGPULimits maxComputeWorkgroupStorageSize(int maxComputeWorkgroupStorageSize) {
		this.io.setIntField(this, 26, maxComputeWorkgroupStorageSize);
		return this;
	}
	@Field(27) 
	public int maxComputeInvocationsPerWorkgroup() {
		return this.io.getIntField(this, 27);
	}
	@Field(27) 
	public WGPULimits maxComputeInvocationsPerWorkgroup(int maxComputeInvocationsPerWorkgroup) {
		this.io.setIntField(this, 27, maxComputeInvocationsPerWorkgroup);
		return this;
	}
	@Field(28) 
	public int maxComputeWorkgroupSizeX() {
		return this.io.getIntField(this, 28);
	}
	@Field(28) 
	public WGPULimits maxComputeWorkgroupSizeX(int maxComputeWorkgroupSizeX) {
		this.io.setIntField(this, 28, maxComputeWorkgroupSizeX);
		return this;
	}
	@Field(29) 
	public int maxComputeWorkgroupSizeY() {
		return this.io.getIntField(this, 29);
	}
	@Field(29) 
	public WGPULimits maxComputeWorkgroupSizeY(int maxComputeWorkgroupSizeY) {
		this.io.setIntField(this, 29, maxComputeWorkgroupSizeY);
		return this;
	}
	@Field(30) 
	public int maxComputeWorkgroupSizeZ() {
		return this.io.getIntField(this, 30);
	}
	@Field(30) 
	public WGPULimits maxComputeWorkgroupSizeZ(int maxComputeWorkgroupSizeZ) {
		this.io.setIntField(this, 30, maxComputeWorkgroupSizeZ);
		return this;
	}
	@Field(31) 
	public int maxComputeWorkgroupsPerDimension() {
		return this.io.getIntField(this, 31);
	}
	@Field(31) 
	public WGPULimits maxComputeWorkgroupsPerDimension(int maxComputeWorkgroupsPerDimension) {
		this.io.setIntField(this, 31, maxComputeWorkgroupsPerDimension);
		return this;
	}
	public WGPULimits(Pointer pointer) {
		super(pointer);
	}
}
