import request from '@/utils/request'

// 查询用户账户列表
export function listAcount(query) {
  return request({
    url: '/system/acount/list',
    method: 'get',
    params: query
  })
}

// 查询用户账户详细
export function getAcount(id) {
  return request({
    url: '/system/acount/' + id,
    method: 'get'
  })
}

// 新增用户账户
export function addAcount(data) {
  return request({
    url: '/system/acount',
    method: 'post',
    data: data
  })
}

// 修改用户账户
export function updateAcount(data) {
  return request({
    url: '/system/acount',
    method: 'put',
    data: data
  })
}

// 删除用户账户
export function delAcount(id) {
  return request({
    url: '/system/acount/' + id,
    method: 'delete'
  })
}

// 导出用户账户
export function exportAcount(query) {
  return request({
    url: '/system/acount/export',
    method: 'get',
    params: query
  })
}