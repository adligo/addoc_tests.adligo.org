package org.adligo.addoc_tests.client.presenter.content;

import org.adligo.addoc.client.i18n.GWTCreateWrapper;
import org.adligo.addoc.client.i18n.TenArticleTrees;
import org.adligo.addoc.client.models.ArticleTreesBuilder;
import org.adligo.addoc.client.models.I_ArticleTree;
import org.adligo.addoc.client.models.IdRange;
import org.adligo.addoc.client.presenter.content.ContentManager;
import org.adligo.addoc.client.presenter.content.I_ArticleTreeRequestor;
import org.adligo.addoc.client.presenter.content.TreesCallback;
import org.adligo.tests4j.shared.asserts.reference.CircularDependencies;
import org.adligo.tests4j.system.shared.trials.SourceFileScope;
import org.adligo.tests4j.system.shared.trials.Test;
import org.adligo.tests4j_4mockito.MockitoSourceFileTrial;
import org.adligo.tests4j_4mockito.MethodRecorder;

import java.util.Map;

@SourceFileScope (sourceClass=TreesCallback.class, allowedCircularDependencies=CircularDependencies.AllowInPackage)
public class TreesCallbackTrial extends MockitoSourceFileTrial {

  @SuppressWarnings({"boxing", "unchecked"})
  @Test
  public void testGetsAndSets() {
    Map<Integer, I_ArticleTree> trees = mock(Map.class);
    TreesCallback cb = new TreesCallback(trees);
    
    assertSame(trees, cb.getArticleTrees());
    assertNull(cb.getIds());
    assertNull(cb.getRequestor());
    assertNull(cb.getTreeId());
    assertNull(cb.getTreesClass());
    
    IdRange ids = mock(IdRange.class);
    cb.setIds(ids);
    assertSame(ids, cb.getIds());
    
    I_ArticleTreeRequestor requestor = mock(I_ArticleTreeRequestor.class);
    cb.setRequestor(requestor);
    assertSame(requestor, cb.getRequestor());
    
    Integer treeId = 1;
    cb.setTreeId(treeId);
    assertSame(treeId, cb.getTreeId());
    
    GWTCreateWrapper<TenArticleTrees> wrapper = mock(GWTCreateWrapper.class);
    cb.setTreesClass(wrapper);
    assertSame(wrapper, cb.getTreesClass());
    
    ArticleTreesBuilder builder = mock(ArticleTreesBuilder.class);
    cb.setTreeBuilder(builder);
    assertSame(builder, cb.getTreeBuilder());
  }
  
  @SuppressWarnings({"boxing", "unchecked"})
  @Test
  public void testOnFailure() {
    Map<Integer, I_ArticleTree> trees = mock(Map.class);
    TreesCallback cb = new TreesCallback(trees);
    I_ArticleTreeRequestor requestor = mock(I_ArticleTreeRequestor.class);
    cb.setRequestor(requestor);
    MethodRecorder<Void> rc = new MethodRecorder<Void>();
    doAnswer(rc).when(requestor).onFailure(any(Throwable.class));
    
    IllegalStateException x = new IllegalStateException();
    cb.onFailure(x);
    assertEquals(1, rc.count());
    assertSame(x, rc.getArguments(0)[0]);
  }
  
  @SuppressWarnings({"boxing", "unchecked"})
  @Test
  public void testOnSuccess() {
    MethodRecorder<Void> addTreesRc = new MethodRecorder<Void>();
    
    Map<Integer, I_ArticleTree> trees = mock(Map.class);
    doAnswer(addTreesRc).when(trees).putAll(any());
    TreesCallback cb = new TreesCallback(trees);
    IdRange ids = new IdRange(0,9);
    cb.setIds(ids);
    
    I_ArticleTreeRequestor requestor = mock(I_ArticleTreeRequestor.class);
    MethodRecorder<Void> rc = new MethodRecorder<Void>();
    doAnswer(rc).when(requestor).onSuccess(any());
    cb.setRequestor(requestor);
    
    Integer treeId = 1;
    cb.setTreeId(treeId);
    
    GWTCreateWrapper<TenArticleTrees> wrapper = mock(GWTCreateWrapper.class);
    cb.setTreesClass(wrapper);
    TenArticleTrees tenArticleTrees = mock(TenArticleTrees.class);
    when(wrapper.create()).thenReturn(tenArticleTrees);
    
    ArticleTreesBuilder builder = mock(ArticleTreesBuilder.class);
    cb.setTreeBuilder(builder);
    Map<Integer, I_ArticleTree> articleTree = mock(Map.class);
    
    when(builder.buildTreeMap(tenArticleTrees, 0, 9)).thenReturn(articleTree);
    
    cb.onSuccess();
    assertEquals(1, addTreesRc.count());
    assertSame(articleTree, addTreesRc.getArguments(0)[0]);
    assertEquals(0, rc.count());
    I_ArticleTree tree = mock(I_ArticleTree.class);
    when(articleTree.get(1)).thenReturn(tree);
    
    cb.onSuccess();
    assertEquals(2, addTreesRc.count());
    assertSame(articleTree, addTreesRc.getArguments(1)[0]);
    assertEquals(1, rc.count());
    assertSame(tree, rc.getArguments(0)[0]);
  }
}
