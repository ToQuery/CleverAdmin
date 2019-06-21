/**
 * 将 list 转为 map
 */
export function list2Map(list) {
  var map = {}
  for (let i = 0; i < list.length; i++) {
    map[list[i].configName] = list[i].configValue
  }
  return map
}

/**
 * 将 map 转为 list
 */
export function map2List(map, configGroup, bizId = undefined) {
  var list = []
  for (var key in map) {
    var obj = {
      configGroup: configGroup,
      configName: key,
      configValue: map[key],
      sortNum: 0
    }
    if (bizId) {
      obj.bizId = bizId
    }
    list.push(obj)
  }
  return list
}

