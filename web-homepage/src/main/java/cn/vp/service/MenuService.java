package cn.vp.service;

import cn.vp.bean.Menu;

import java.util.List;


/**
 * 菜单管理
 */
public interface MenuService {
    /**
     * 新增
     * @param menu 菜单
     * @return 返回操作的条数
     */
    public int addMenu(Menu menu);


    /**
     * 根据id查询菜单
     * @param id 菜单id
     * @return 菜单
     */
    public Menu getMenu(int id);

    /**
     * 查询所有
     * @return 菜单集合
     */
    public List<Menu> getMenus();

    /**
     * 批量删除
     * @param ids 需要删除的id集合
     * @return 删除的条数
     */
    public int delectMenu(Integer[] ids);

    /**
     * 修改
     * @param menu 菜单
     * @return 修改的条数
     */
    public int updateMenu(Menu menu);



    List<Menu> getParentMenu();

}
