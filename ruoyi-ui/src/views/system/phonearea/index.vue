<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="手机前缀" prop="prePhone">
        <el-input
          v-model="queryParams.prePhone"
          placeholder="请输入手机前缀"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地区区号" prop="areaCode">
        <el-select v-model="queryParams.areaCode" placeholder="请选择地区区号" clearable size="small">
          <el-option
            v-for="dict in areaCodeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="运营商" prop="company">
        <el-select v-model="queryParams.company" placeholder="请选择运营商" clearable size="small">
          <el-option
            v-for="dict in companyOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="更新人" prop="updateUserId">
        <el-input
          v-model="queryParams.updateUserId"
          placeholder="请输入更新人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:phonearea:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:phonearea:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:phonearea:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:phonearea:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="phoneareaList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="手机前缀" align="center" prop="prePhone" />
      <el-table-column label="地区区号" align="center" prop="areaCode" :formatter="areaCodeFormat" />
      <el-table-column label="运营商" align="center" prop="company" :formatter="companyFormat" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:phonearea:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:phonearea:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改手机地区对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="手机前缀" prop="prePhone">
          <el-input v-model="form.prePhone" placeholder="请输入手机前缀" />
        </el-form-item>
        <el-form-item label="地区区号" prop="areaCode">
          <el-select v-model="form.areaCode" placeholder="请选择地区区号">
            <el-option
              v-for="dict in areaCodeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="运营商" prop="company">
          <el-select v-model="form.company" placeholder="请选择运营商">
            <el-option
              v-for="dict in companyOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPhonearea, getPhonearea, delPhonearea, addPhonearea, updatePhonearea, exportPhonearea } from "@/api/system/phonearea";

export default {
  name: "Phonearea",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 手机地区表格数据
      phoneareaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 地区区号字典
      areaCodeOptions: [],
      // 运营商字典
      companyOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        prePhone: null,
        areaCode: null,
        areaName: null,
        company: null,
        updateUserId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("az_areadata").then(response => {
      this.areaCodeOptions = response.data;
    });
    this.getDicts("sys_company_type").then(response => {
      this.companyOptions = response.data;
    });
  },
  methods: {
    /** 查询手机地区列表 */
    getList() {
      this.loading = true;
      listPhonearea(this.queryParams).then(response => {
        this.phoneareaList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 地区区号字典翻译
    areaCodeFormat(row, column) {
      return this.selectDictLabel(this.areaCodeOptions, row.areaCode);
    },
    // 运营商字典翻译
    companyFormat(row, column) {
      return this.selectDictLabel(this.companyOptions, row.company);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        prePhone: null,
        areaCode: null,
        areaName: null,
        company: null,
        remark: null,
        updateTime: null,
        updateUserId: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加手机地区";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPhonearea(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改手机地区";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePhonearea(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPhonearea(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除手机地区编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPhonearea(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有手机地区数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportPhonearea(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
