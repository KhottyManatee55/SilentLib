package net.silentchaos512.lib.client.model;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IModel;
import net.silentchaos512.lib.SilentLib;

public abstract class LayeredBakedModel implements IBakedModel {

  protected final IModel parent;
  protected final ImmutableList<ImmutableList<BakedQuad>> quads;
  protected final VertexFormat format;

  public LayeredBakedModel(IModel parent, ImmutableList<ImmutableList<BakedQuad>> immutableList, VertexFormat format) {

    this.quads = immutableList;
    this.format = format;
    this.parent = parent;
  }

  public int getLayerCount() {

    return quads.size();
  }

  @Override
  public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long indexNotRand) {

    if (side != null)
      return ImmutableList.of();

    int index = (int) indexNotRand;
    List<BakedQuad> list = this.quads.get(index);

    if (list == null)
      return ImmutableList.of();

    return list;
  }

  @Override
  public boolean isBuiltInRenderer() {

    return true;
  }

  public IModel getParent() {

    return this.parent;
  }

  public VertexFormat getVertexFormat() {

    return this.format;
  }
}