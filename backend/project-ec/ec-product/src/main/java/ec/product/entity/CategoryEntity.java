package ec.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品三级分类
 *
 * @author zack.zhang
 * @email zzhang_xz@163.com
 * @date 2020-10-05 22:36:26
 */
@Data
@TableName("pms_category")
public class CategoryEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId private Long catId;
  private String name;
  private Long parentCid;
  private Integer catLevel;
  private Integer showStatus;
  private Integer sort;
  private String icon;
  private String productUnit;
  private Integer productCount;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  @TableLogic private Integer isDeleted;

  @TableField(exist = false)
  private List<CategoryEntity> children;
}
