package cn.vp.service.impl;

import cn.vp.bean.Menu;
import cn.vp.dao.MenuMapper;
import cn.vp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单管理
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    /**
     * 新增
     * @param menu 菜单
     * @return 返回操作的条数
     */
    public int addMenu(Menu menu){
        return menuMapper.insertSelective(menu);
    }


    /**
     * 根据id查询菜单
     * @param id 菜单id
     * @return 菜单
     */
    public Menu getMenu(int id){
        return menuMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有
     * @return 菜单集合
     */
    public List<Menu> getMenus(){
        return menuMapper.selectMenus();
    }

    /**
     * 批量删除
     * @param ids 需要删除的id集合
     * @return 删除的条数
     */
    public int delectMenu(Integer[] ids){
        return menuMapper.delectByIds(ids);
    }

    /**
     * 修改
     * @param menu 菜单
     * @return 修改的条数
     */
    public int updateMenu(Menu menu){
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * 获取父级菜单
     * @return 父级菜单
     */
    @Override
    public List<Menu> getParentMenu() {
        return menuMapper.getParentMenu();
    }


//    @Override
//    public EasyUiDataGridResult getItemList(int page, int rows) {
//        PageHelper.startPage(page, rows);
//        List<Menu> menus = menuMapper.selectMenus();
//        EasyUiDataGridResult easyUiDataGridResult=new EasyUiDataGridResult();
//        easyUiDataGridResult.setRows(menus);
//        PageInfo<Menu> pageInfo=new PageInfo<>(menus);
//
//        easyUiDataGridResult.setTotal((int) pageInfo.getTotal());
//        return easyUiDataGridResult;
//    }
}
