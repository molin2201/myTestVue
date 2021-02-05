<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="拦截类型" prop="filterType">
        <el-select v-model="queryParams.filterType" placeholder="请选择拦截类型" clearable size="small">
          <el-option
            v-for="dict in filterTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="日期">
        <el-date-picker
          v-model="daterangeInsertDate"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="接口" prop="interfaceId">
        <el-select v-model="queryParams.interfaceId" placeholder="请选择接口ID" clearable size="small">
          <el-option
            v-for="dict in interfaceIdOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="地区" prop="areaIn">
        <el-select v-model="queryParams.areaIn" placeholder="请选择地区" clearable size="small">
          <el-option
            v-for="dict in areaInOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="用户" prop="userCode">
        <el-input
          v-model="queryParams.userCode"
          placeholder="请输入用户"
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
          v-hasPermi="['system:hislog:add']"
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
          v-hasPermi="['system:hislog:edit']"
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
          v-hasPermi="['system:hislog:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:hislog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="hislogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="拦截类型" align="center" prop="filterType" :formatter="filterTypeFormat" />
      <el-table-column label="请求次数" align="center" prop="reqCount" />
      <el-table-column label="命中次数" align="center" prop="filterCount" />
      <el-table-column label="日期" align="center" prop="insertDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.insertDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="接口" align="center" prop="interfaceId" :formatter="interfaceIdFormat"/>
      <el-table-column label="地区" align="center" prop="areaIn" :formatter="areaInFormat" />
      <el-table-column label="用户" align="center" prop="userCode" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:hislog:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:hislog:remove']"
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

    <!-- 添加或修改拦截日志对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="拦截类型" prop="filterType">
          <el-select v-model="form.filterType" placeholder="请选择拦截类型">
            <el-option
              v-for="dict in filterTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="请求次数" prop="reqCount">
          <el-input v-model="form.reqCount" placeholder="请输入请求次数" />
        </el-form-item>
        <el-form-item label="命中次数" prop="filterCount">
          <el-input v-model="form.filterCount" placeholder="请输入命中次数" />
        </el-form-item>
        <el-form-item label="日期" prop="insertDate">
          <el-date-picker clearable size="small"
            v-model="form.insertDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="接口" prop="interfaceId">
          <el-select v-model="form.interfaceId" placeholder="请选择接口ID">
            <el-option
              v-for="dict in interfaceIdOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="地区" prop="areaIn">
          <el-select v-model="form.areaIn" placeholder="请选择地区">
            <el-option
              v-for="dict in areaInOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户" prop="userCode">
          <el-input v-model="form.userCode" placeholder="请输入用户" />
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
import { listHislog, getHislog, delHislog, addHislog, updateHislog, exportHislog } from "@/api/system/hislog";

export default {
  name: "Hislog",
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
      // 拦截日志表格数据
      hislogList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 拦截类型字典
      filterTypeOptions: [],
      // 日期时间范围
      daterangeInsertDate: [],
      // 接口ID字典
      interfaceIdOptions: [],
      // 地区字典
      areaInOptions: [],

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        filterType: null,
        insertDate: null,
        interfaceId: null,
        areaIn: null,
        userCode: null
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
    this.getDicts("az_interfacedata").then(response => {
      this.interfaceIdOptions = response.data;
    });
    this.getDicts("az_filter_type").then(response => {
      this.filterTypeOptions = response.data;
    });
    this.getDicts("az_areadata").then(response => {
      this.areaInOptions = response.data;
    });
  },
  methods: {
    /** 查询拦截日志列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeInsertDate && '' != this.daterangeInsertDate) {
        this.queryParams.params["beginInsertDate"] = this.daterangeInsertDate[0];
        this.queryParams.params["endInsertDate"] = this.daterangeInsertDate[1];
      }
      listHislog(this.queryParams).then(response => {
        this.hislogList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 接口ID字典翻译
    interfaceIdFormat(row, column) {
      return this.selectDictLabel(this.interfaceIdOptions, row.interfaceId);
    },
    // 拦截类型字典翻译
    filterTypeFormat(row, column) {
      return this.selectDictLabel(this.filterTypeOptions, row.filterType);
    },
    // 地区字典翻译
    areaInFormat(row, column) {
      return this.selectDictLabel(this.areaInOptions, row.areaIn);
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
        filterType: null,
        reqCount: null,
        filterCount: null,
        insertDate: null,
        interfaceId: null,
        areaIn: null,
        userCode: null
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
      this.daterangeInsertDate = [];
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
      this.title = "添加拦截日志";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getHislog(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改拦截日志";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateHislog(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHislog(this.form).then(response => {
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
      this.$confirm('是否确认删除拦截日志编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delHislog(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有拦截日志数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportHislog(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
