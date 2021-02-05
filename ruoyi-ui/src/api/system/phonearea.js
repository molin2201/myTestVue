import request from '@/utils/request'

// 查询手机地区列表
export function listPhonearea(query) {
  return request({
    url: '/system/phonearea/list',
    method: 'get',
    params: query
  })
}

// 查询手机地区详细
export function getPhonearea(id) {
  return request({
    url: '/system/phonearea/' + id,
    method: 'get'
  })
}

// 新增手机地区
export function addPhonearea(data) {
  return request({
    url: '/system/phonearea',
    method: 'post',
    data: data
  })
}

// 修改手机地区
export function updatePhonearea(data) {
  return request({
    url: '/system/phonearea',
    method: 'put',
    data: data
  })
}

// 删除手机地区
export function delPhonearea(id) {
  return request({
    url: '/system/phonearea/' + id,
    method: 'delete'
  })
}

// 导出手机地区
export function exportPhonearea(query) {
  return request({
    url: '/system/phonearea/export',
    method: 'get',
    params: query
  })
}