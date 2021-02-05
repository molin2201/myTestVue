import request from '@/utils/request'

// 查询充值记录列表
export function listHis(query) {
  return request({
    url: '/system/his/list',
    method: 'get',
    params: query
  })
}

// 查询充值记录详细
export function getHis(id) {
  return request({
    url: '/system/his/' + id,
    method: 'get'
  })
}

// 新增充值记录
export function addHis(data) {
  return request({
    url: '/system/his',
    method: 'post',
    data: data
  })
}

// 修改充值记录
export function updateHis(data) {
  return request({
    url: '/system/his',
    method: 'put',
    data: data
  })
}

// 删除充值记录
export function delHis(id) {
  return request({
    url: '/system/his/' + id,
    method: 'delete'
  })
}

// 导出充值记录
export function exportHis(query) {
  return request({
    url: '/system/his/export',
    method: 'get',
    params: query
  })
}