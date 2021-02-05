<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="拦截名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入拦截名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="拦截类型" prop="ruleType">
        <el-select v-model="queryParams.ruleType" placeholder="请选择拦截类型" clearable size="small">
          <el-option
            v-for="dict in ruleTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="地区" prop="areaId">
        <el-select v-model="queryParams.areaId" placeholder="请选择地区" clearable size="small">
          <el-option
            v-for="dict in areaIdOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否启用" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择是否启用" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
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
          v-hasPermi="['system:rule:add']"
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
          v-hasPermi="['system:rule:edit']"
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
          v-hasPermi="['system:rule:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:rule:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ruleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="拦截名称" align="center" prop="name" />
      <el-table-column label="说明" align="center" prop="remark" />
      <el-table-column label="拦截类型" align="center" prop="ruleType" :formatter="ruleTypeFormat" />
      <el-table-column label="地区" align="center" prop="areaId" :formatter="areaIdFormat" />
      <el-table-column label="运营商" align="center" prop="companyId" :formatter="companyIdFormat" />
      <el-table-column label="号码规则" align="center" prop="numruleId" :formatter="numruleIdFormat" />
      <el-table-column label="限制天数" align="center" prop="limitDay" />
      <el-table-column label="备用1" align="center" prop="field1" />
      <el-table-column label="备用2" align="center" prop="field2" />
      <el-table-column label="创建人" align="center" prop="createUserId" />
      <el-table-column label="是否启用" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:rule:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:rule:remove']"
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

    <!-- 添加或修改拦截规则对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="拦截名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入拦截名称" />
        </el-form-item>
        <el-form-item label="说明" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="拦截类型" prop="ruleType">
          <el-select v-model="form.ruleType" placeholder="请选择拦截类型">
            <el-option
              v-for="dict in ruleTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="地区" prop="areaId">
          <el-select v-model="form.areaId" placeholder="请选择地区">
            <el-option
              v-for="dict in areaIdOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="运营商">
          <el-checkbox-group v-model="form.companyId">
            <el-checkbox
              v-for="dict in companyIdOptions"
              :key="dict.dictValue"
              :label="dict.dictValue">
              {{dict.dictLabel}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="号码规则">
          <el-checkbox-group v-model="form.numruleId">
            <el-checkbox
              v-for="dict in numruleIdOptions"
              :key="dict.dictValue"
              :label="dict.dictValue">
              {{dict.dictLabel}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="限制天数" prop="limitDay">
          <el-input v-model="form.limitDay" placeholder="请输入限制天数" />
        </el-form-item>
        <el-form-item label="备用1" prop="field1">
          <el-input v-model="form.field1" placeholder="请输入备用1" />
        </el-form-item>
        <el-form-item label="备用2" prop="field2">
          <el-input v-model="form.field2" placeholder="请输入备用2" />
        </el-form-item>
        <el-form-item label="是否启用">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
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
import { listRule, getRule, delRule, addRule, updateRule, exportRule } from "@/api/system/rule";

export default {
  name: "Rule",
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
      // 拦截规则表格数据
      ruleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 拦截类型字典
      ruleTypeOptions: [],
      // 地区字典
      areaIdOptions: [],
      // 运营商字典
      companyIdOptions: [],
      // 号码规则字典
      numruleIdOptions: [],
      // 是否启用字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        ruleType: null,
        areaId: null,
        companyId: null,
        numruleId: null,
        status: null
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
    this.getDicts("az_filter_type").then(response => {
      this.ruleTypeOptions = response.data;
    });
    this.getDicts("az_areadata").then(response => {
      this.areaIdOptions = response.data;
    });
    this.getDicts("sys_company_type").then(response => {
      this.companyIdOptions = response.data;
    });
    this.getDicts("sys_phone_rule").then(response => {
      this.numruleIdOptions = response.data;
    });
    this.getDicts("sys_master_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询拦截规则列表 */
    getList() {
      this.loading = true;
      listRule(this.queryParams).then(response => {
        this.ruleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 拦截类型字典翻译
    ruleTypeFormat(row, column) {
      return this.selectDictLabel(this.ruleTypeOptions, row.ruleType);
    },
    // 地区字典翻译
    areaIdFormat(row, column) {
      return this.selectDictLabel(this.areaIdOptions, row.areaId);
    },
    // 运营商字典翻译
    companyIdFormat(row, column) {
      return this.selectDictLabels(this.companyIdOptions, row.companyId);
    },
    // 号码规则字典翻译
    numruleIdFormat(row, column) {
      return this.selectDictLabels(this.numruleIdOptions, row.numruleId);
    },
    // 是否启用字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
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
        name: null,
        remark: null,
        ruleType: null,
        areaId: null,
        companyId: [],
        numruleId: [],
        limitDay: null,
        field1: null,
        field2: null,
        createUserId: null,
        createTime: null,
        status: "0"
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
      this.title = "添加拦截规则";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRule(id).then(response => {
        this.form = response.data;
        this.form.companyId = this.form.companyId.split(",");
        this.form.numruleId = this.form.numruleId.split(",");
        this.open = true;
        this.title = "修改拦截规则";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.companyId = this.form.companyId.join(",");
          this.form.numruleId = this.form.numruleId.join(",");
          if (this.form.id != null) {
            updateRule(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRule(this.form).then(response => {
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
      this.$confirm('是否确认删除拦截规则编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRule(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有拦截规则数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportRule(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
