import request from '@/utils/request'

// 查询外呼记录列表
export function listCallhis(query) {
  return request({
    url: '/system/callhis/list',
    method: 'get',
    params: query
  })
}

// 查询外呼记录详细
export function getCallhis(id) {
  return request({
    url: '/system/callhis/' + id,
    method: 'get'
  })
}

// 新增外呼记录
export function addCallhis(data) {
  return request({
    url: '/system/callhis',
    method: 'post',
    data: data
  })
}

// 修改外呼记录
export function updateCallhis(data) {
  return request({
    url: '/system/callhis',
    method: 'put',
    data: data
  })
}

// 删除外呼记录
export function delCallhis(id) {
  return request({
    url: '/system/callhis/' + id,
    method: 'delete'
  })
}

// 导出外呼记录
export function exportCallhis(query) {
  return request({
    url: '/system/callhis/export',
    method: 'get',
    params: query
  })
}