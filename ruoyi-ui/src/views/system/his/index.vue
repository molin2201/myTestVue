<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="充值时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="充值用户" prop="userCode">
        <el-input
          v-model="queryParams.userCode"
          placeholder="请输入充值用户"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="接口ID" prop="interfaceId">
        <el-select v-model="queryParams.interfaceId" placeholder="请选择接口ID" clearable size="small">
          <el-option
            v-for="dict in interfaceIdOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="订单号" prop="orderNo">
        <el-input
          v-model="queryParams.orderNo"
          placeholder="请输入订单号"
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
          v-hasPermi="['system:his:add']"
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
          v-hasPermi="['system:his:edit']"
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
          v-hasPermi="['system:his:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:his:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="hisList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单号" align="center" prop="id" />
      <el-table-column label="充值时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="次数" align="center" prop="count" />
      <el-table-column label="单价" align="center" prop="unitPrice" />
      <el-table-column label="总价" align="center" prop="amt" />
      <el-table-column label="充值人" align="center" prop="createUserId" />
      <el-table-column label="说明" align="center" prop="remark" />
      <el-table-column label="操作人" align="center" prop="userCode" />
      <el-table-column label="接口ID" align="center" prop="interfaceId" :formatter="interfaceIdFormat" />
      <el-table-column label="支付方式" align="center" prop="payType" :formatter="payTypeFormat" />
      <el-table-column label="订单号" align="center" prop="orderNo" />
      <el-table-column label="订单状态" align="center" prop="orderSatus" :formatter="orderSatusFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:his:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:his:remove']"
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

    <!-- 添加或修改充值记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="次数" prop="count">
          <el-input v-model="form.count" placeholder="请输入次数" />
        </el-form-item>
        <el-form-item label="单价" prop="unitPrice">
          <el-input v-model="form.unitPrice" placeholder="请输入单价" />
        </el-form-item>
        <el-form-item label="总价" prop="amt">
          <el-input v-model="form.amt" placeholder="请输入总价" />
        </el-form-item>
        <el-form-item label="充值人" prop="createUserId">
          <el-input v-model="form.createUserId" placeholder="请输入充值人" />
        </el-form-item>
        <el-form-item label="说明" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入说明" />
        </el-form-item>
        <el-form-item label="操作人" prop="userCode">
          <el-input v-model="form.userCode" placeholder="请输入操作人" />
        </el-form-item>
        <el-form-item label="接口ID" prop="interfaceId">
          <el-select v-model="form.interfaceId" placeholder="请选择接口ID">
            <el-option
              v-for="dict in interfaceIdOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="支付方式" prop="payType">
          <el-select v-model="form.payType" placeholder="请选择支付方式">
            <el-option
              v-for="dict in payTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订单号" prop="orderNo">
          <el-input v-model="form.orderNo" placeholder="请输入订单号" />
        </el-form-item>
        <el-form-item label="订单状态" prop="orderSatus">
          <el-select v-model="form.orderSatus" placeholder="请选择订单状态">
            <el-option
              v-for="dict in orderSatusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
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
import { listHis, getHis, delHis, addHis, updateHis, exportHis } from "@/api/system/his";

export default {
  name: "His",
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
      // 充值记录表格数据
      hisList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 充值时间时间范围
      daterangeCreateTime: [],
      // 接口ID字典
      interfaceIdOptions: [],
      // 支付方式字典
      payTypeOptions: [],
      // 订单状态字典
      orderSatusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        createTime: null,
        userCode: null,
        interfaceId: null,
        orderNo: null,
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
    this.getDicts("sys_pay_type").then(response => {
      this.payTypeOptions = response.data;
    });
    this.getDicts("sys_order_status").then(response => {
      this.orderSatusOptions = response.data;
    });
  },
  methods: {
    /** 查询充值记录列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listHis(this.queryParams).then(response => {
        this.hisList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 接口ID字典翻译
    interfaceIdFormat(row, column) {
      return this.selectDictLabel(this.interfaceIdOptions, row.interfaceId);
    },
    // 支付方式字典翻译
    payTypeFormat(row, column) {
      return this.selectDictLabel(this.payTypeOptions, row.payType);
    },
    // 订单状态字典翻译
    orderSatusFormat(row, column) {
      return this.selectDictLabel(this.orderSatusOptions, row.orderSatus);
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
        createTime: null,
        count: null,
        unitPrice: null,
        amt: null,
        createUserId: null,
        remark: null,
        userCode: null,
        interfaceId: null,
        payType: null,
        orderNo: null,
        orderSatus: null
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
      this.daterangeCreateTime = [];
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
      this.title = "添加充值记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getHis(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改充值记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateHis(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHis(this.form).then(response => {
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
      this.$confirm('是否确认删除充值记录编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delHis(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有充值记录数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportHis(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
