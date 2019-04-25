<template>
    <div class="app-container">
        <div class="filter-container">
            <el-input :placeholder="$t('filter.system.user.userName')" v-model="filterForm.title" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter"/>
            <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">{{ $t('table.search') }}</el-button>
            <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" >{{ $t('table.add') }}</el-button>
        </div>
        <el-table :data="tableData.content" border style="width: 100%">
            <el-table-column prop="date" label="日期" width="180" />
            <el-table-column prop="name" label="姓名" width="180" />
            <el-table-column prop="address" label="地址" />
        </el-table>
        <pagination v-show="tableData.page.totalElements > 0" :total="tableData.page.totalElements" :page-size="tableData.page.pageSize" :page-sizes="[15, 30, 50, 100]" @pagination="getList" />
    </div>

</template>
<script>
    export default {
        data() {
            return {
                listLoading: true,
                filterForm: {
                    title: ''
                },
                tableData: {
                    page: {
                        pageSize: 20,
                        pageNumber: 0,
                        totalElements: 20,
                        totalPages:0
                    },
                    content: [{
                        date: '2016-05-02',
                        name: '王小虎123',
                        address: '上海市普陀区金沙江路 1518 弄'
                    }, {
                        date: '2016-05-04',
                        name: '王小虎',
                        address: '上海市普陀区金沙江路 1517 弄'
                    }, {
                        date: '2016-05-01',
                        name: '王小虎',
                        address: '上海市普陀区金沙江路 1519 弄'
                    }, {
                        date: '2016-05-03',
                        name: '王小虎',
                        address: '上海市普陀区金沙江路 1516 弄'
                    }]
                }
            };
        },methods: {
            handleFilter() {
                this.getList();
            },
            getList() {
                this.listLoading = true;
                setTimeout(() => {
                    this.listLoading = false
                }, 1.5 * 1000)
            },
        }
    };
</script>
<style scoped>

</style>