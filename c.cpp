#include<iostream>
#include<vector>
using namespace std;


class BloomFilter {
  private:
    int _thres;
    int _len;
    int _current;
    vector<vector<bool> > _data;
    vector<int (*)(string)> _hash;
  public:
  BloomFilter(int k, vector<int (*)(string)> hash, int thres) {
    _len = k;
    _data = vector<vector<bool> >(1,vector<bool>(k,false));
    _hash = hash;
    _thres = thres;
    _current = 0;
  }
  void add(string s) {
    if (_current >= _thres) {
      _data.push_back(vector<bool>(_len,false));
      _current = 0;
    }
    auto data = _data.back();
    for (auto h : _hash) {
      if (!data[h(s) % _len]) {
        data[h(s)% _len] = true;
        _current++;
      }
    }
  }
  bool mightContain(string s,vector<bool> data) {
    for (auto h : _hash) {
      if (!data[h(s) % _len]) {
        return false;
      }
    }
    return true;
  }
  bool mightContain(string s) {
    for (auto data : _data) {
      if (mightContain(s, data)) {
        return true;
      }
    }
    return false;
  }
};

int main()
{
}
