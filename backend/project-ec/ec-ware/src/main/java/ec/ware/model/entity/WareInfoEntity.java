package ec.ware.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 仓库信息
 *
 * @author zack.zhang <br>
 * @create 2020-12-19 22:14:28 <br>
 * @project ware <br>
 */
@Data
@TableName("wms_ware_info")
public class WareInfoEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId private Long id;
  private String name;
  private String address;
  private String areacode;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  @TableLogic private Integer isDeleted;
}
