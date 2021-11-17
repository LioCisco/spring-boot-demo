local key = KEYS[1]
local now = tonumber(ARGV[1])
local ttl = tonumber(ARGV[2])
local expired = tonumber(ARGV[3])

local max = tonumber(ARGV[4])

redis.call('zremrangebyscore',key,0,expired)

local current = tonumber(redis.call('zcard',key))
local next = current + 1

if next > max then
    return 0;
else
    redis.call("zadd",key,now,now)
    redis.call("pexpire",key,ttl)
    return next
end